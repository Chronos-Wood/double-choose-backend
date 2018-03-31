package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.cache.key.AccountKey;
import com.chronoswood.doublechoose.dao.AccountDao;
import com.chronoswood.doublechoose.exception.BizException;
import com.chronoswood.doublechoose.model.AccountDO;
import com.chronoswood.doublechoose.model.AccountVO;
import com.chronoswood.doublechoose.model.Message;
import com.chronoswood.doublechoose.model.TokenVO;
import com.chronoswood.doublechoose.service.AccountService;
import com.chronoswood.doublechoose.service.RedisService;
import com.chronoswood.doublechoose.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountDao accountDao;

    private RedisService redisService;

    public AccountServiceImpl(AccountDao accountDao, RedisService redisService) {
        this.accountDao = accountDao;
        this.redisService = redisService;
    }

    @Override
    public TokenVO login(AccountVO accountVO, HttpServletResponse response) {
        String userName = accountVO.getUserName();

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
        return new TokenVO(token);
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
    public Message register(AccountVO accountVO) {
        String userName = accountVO.getUserName();
        String password = accountVO.getPassword();
        Integer role = accountVO.getRole();

        if(userName == null) {
            return Message.USER_NAME_REQUIRED;
        }
        if (password == null) {
            return Message.PASSWORD_REQUIRED;
        }
        if (role == null) {
            return Message.ROLE_REQUIRED;
        }
        AccountDO account = accountDao.getUserByUsername(userName);
        if (account != null) {
            return Message.USER_EXIST;
        }
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        AccountDO newAccount = new AccountDO();
        newAccount.setPassword(MD5Util.formPass2DBPass(password, salt));
        newAccount.setSalt(salt);
        newAccount.setCreateTime(LocalDateTime.now());
        newAccount.setRole(role);
        newAccount.setUserName(userName);
        if (accountDao.register(newAccount) > 0) {
            return Message.SUCCESS;
        }
        return Message.SERVER_ERROR;
    }
}
