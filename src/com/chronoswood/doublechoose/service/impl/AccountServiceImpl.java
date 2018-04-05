package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.cache.key.AccountKey;
import com.chronoswood.doublechoose.dao.AccountDao;
import com.chronoswood.doublechoose.exception.BizException;
import com.chronoswood.doublechoose.model.*;
import com.chronoswood.doublechoose.service.AccountService;
import com.chronoswood.doublechoose.service.DirectorService;
import com.chronoswood.doublechoose.service.RedisService;
import com.chronoswood.doublechoose.service.StudentService;
import com.chronoswood.doublechoose.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService{

    private static final int NOW_ROWS_AFFECTED = 0;
    private static final int UNAUTHORIZED = 0;
    private static final boolean OPERATION_SUCCESS = true;

    private AccountDao accountDao;

    private RedisService redisService;

    private StudentService studentService;

    private DirectorService directorService;

    public AccountServiceImpl(AccountDao accountDao,
                              RedisService redisService,
                              StudentService studentService,
                              DirectorService directorService) {
        this.accountDao = accountDao;
        this.redisService = redisService;
        this.studentService = studentService;
        this.directorService = directorService;
    }

    @Override
    public TokenVO login(AccountVO accountVO, HttpServletResponse response) {
        String userName = accountVO.getUserName();
        Integer role = accountVO.getRole();

        if(!StringUtils.hasText(userName)) {
            throw new BizException(Message.USER_NAME_REQUIRED);
        }
        AccountDO account = accountDao.getUserByUsername(userName);
        if(account == null) {
            throw new BizException(Message.USER_NOT_EXIST);
        }
        String password = accountVO.getPassword();

        String calcPass = MD5Util.formPass2DBPass(password, account.getSalt());

        if(!Objects.equals(calcPass, account.getPassword())) {
            throw new BizException(Message.WRONG_PASSWORD);
        }

        //生成token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        //生成cookie
        setToken(account, token, response);
        return new TokenVO(token, role, userName);
    }

    @Override
    public AccountDO getUserByToken(String token, HttpServletResponse response) {
        if (!StringUtils.hasText(token)) {
            return null;
        }
        AccountDO account = redisService.get(AccountKey.tokenKey, token, AccountDO.class);

        if (account != null) {
            //刷新token 延长token时间
            setToken(account, token, response);
        }
        //TODO update user
        return account;
    }


    private void setToken(AccountDO account, String token, HttpServletResponse response) {
        redisService.set(AccountKey.tokenKey, token, account);
        response.setHeader(AccountKey.TOKEN_NAME, token);
    }

    @Override
    @Transactional
    public boolean register(SignUpVO signUpVO) {
        AccountVO accountVO = signUpVO.getAccountVO();
        StudentSignUpVO studentSignUpVO = signUpVO.getStudentSignUpVO();
        StaffSignUpVO staffSignUpVO = signUpVO.getStaffSignUpVO();
        String userName = accountVO.getUserName();
        String password = accountVO.getPassword();
        Integer role = accountVO.getRole();

        if(userName == null) {
            throw new BizException( Message.USER_NAME_REQUIRED);
        }
        if (password == null) {
            throw new BizException( Message.PASSWORD_REQUIRED);
        }
        if (role == null) {
            throw new BizException( Message.ROLE_REQUIRED);
        }
        AccountDO account = accountDao.getUserByUsername(userName);
        if (account != null) {
            throw new BizException( Message.USER_EXIST);
        }
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        AccountDO newAccount = new AccountDO();

        newAccount.setPassword(MD5Util.formPass2DBPass(password, salt));
        newAccount.setSalt(salt);
        newAccount.setCreateTime(LocalDateTime.now());
        newAccount.setRole(role);
        newAccount.setUserName(userName);
        newAccount.setAuthorized(UNAUTHORIZED);
        int affectedRows = 0;
        switch (Role.getRole(role)) {
            case STUDENT:
                Student student = new Student();
                student.setUserName(userName);
                student.setName(studentSignUpVO.getStudentName());
                student.setGender(studentSignUpVO.getStudentSex());
                affectedRows = studentService.addStudent(student);
                break;
            case ADMIN:
                break;
            case STAFF:
                Director director = new Director();
                director.setUserName(userName);
                director.setName(staffSignUpVO.getStaffName());
                director.setGender(staffSignUpVO.getStaffSex());
                director.setCollege(staffSignUpVO.getCollege());
                director.setTitle(staffSignUpVO.getTitle());
                affectedRows = directorService.addDirector(director);
            default:
        }
        if (affectedRows == 0) {
            throw new BizException(Message.INSERT_ERROR);
        }
        if (accountDao.register(newAccount) > NOW_ROWS_AFFECTED) {
            return OPERATION_SUCCESS;
        }
        throw new BizException(Message.SERVER_ERROR);
    }

}
