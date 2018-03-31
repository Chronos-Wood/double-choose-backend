package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.dao.StudentDao;
import com.chronoswood.doublechoose.exception.BizException;
import com.chronoswood.doublechoose.model.Student;
import com.chronoswood.doublechoose.service.StudentService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public Student queryStudentByUsername(@NonNull String userName) {
        try{
            return studentDao.queryStudentByUsername(userName);
        }catch (Exception e){
            log.error("",e);
            throw new BizException(e);
        }
    }

    @Override
    public int addStudent(Student student) {
        return studentDao.addStudent(student);
    }
}
