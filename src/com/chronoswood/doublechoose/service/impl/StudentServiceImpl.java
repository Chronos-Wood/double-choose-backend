package com.chronoswood.doublechoose.service.impl;

import com.chronoswood.doublechoose.cache.key.StudentKey;
import com.chronoswood.doublechoose.cache.key.ProjectKey;
import com.chronoswood.doublechoose.dao.StudentDao;
import com.chronoswood.doublechoose.dao.ProjectsDao;
import com.chronoswood.doublechoose.exception.BizException;
import com.chronoswood.doublechoose.model.Student;
import com.chronoswood.doublechoose.model.Projects;
import com.chronoswood.doublechoose.service.RedisService;
import com.chronoswood.doublechoose.service.StudentService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ProjectsDao projectsDao;


    @Override
    public Student queryStudentByUsername(String userName) {
//        if(!Objects.equals(MDC.get("userName"), userName)){
//            throw new BizException("无访问权限");
//        }
        Student result = null;
        try{
            //先查缓存
            result = redisService.get(StudentKey.studentKeyPrefix, userName, Student.class);
            if (result != null) {
                return result;
            }
            result = studentDao.queryStudentByUsername(userName);
        }catch (Exception e){
            log.error("查询学生信息失败",e);
            throw new BizException("查询学生信息失败");
        }
        if(result==null){
            throw new BizException("无法查询到相关学生信息");
        }
        //入缓存
        redisService.set(StudentKey.studentKeyPrefix, userName, result);
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
            int affectedRows =  studentDao.updateStudentInfo(student);
            if (affectedRows > 0) {
                //删除缓存
                redisService.remove(StudentKey.studentKeyPrefix, student.getUserName());
            }
            return affectedRows;
        }catch (Exception e){
            log.error("更新学生信息失败",e);
            throw new BizException("更新学生信息失败");
        }
    }

    @Override
    public List<Student> listStudents(int offset, int amount) {
        try{
            return studentDao.listStudents(offset, amount);
        }catch (Exception e){
            log.error("查询导师列表失败",e);
            throw new BizException("查询导师列表失败");
        }
    }
}
