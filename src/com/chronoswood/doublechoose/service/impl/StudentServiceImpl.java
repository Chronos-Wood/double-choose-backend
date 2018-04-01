package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.dao.StudentDao;
import com.chronoswood.doublechoose.exception.BizException;
import com.chronoswood.doublechoose.model.Student;
import com.chronoswood.doublechoose.service.StudentService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public Student queryStudentByUsername(String userName) {
        if(!Objects.equals(MDC.get("userName"), userName)){
            throw new BizException("无访问权限");
        }
        Student result = null;
        try{
            result = studentDao.queryStudentByUsername(userName);
        }catch (Exception e){
            log.error("查询学生信息失败",e);
            throw new BizException("查询学生信息失败");
        }
        if(result==null){
            throw new BizException("无法查询到相关学生信息");
        }
        return result;
    }

    @Override
    public int addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        if(!Objects.equals(MDC.get("userName"), student.getUserName())){
            throw new BizException("无访问权限");
        }
        try{
            return studentDao.updateStudentInfo(student);
        }catch (Exception e){
            log.error("更新学生信息失败",e);
            throw new BizException("更新学生信息失败");
        }
    }
}
