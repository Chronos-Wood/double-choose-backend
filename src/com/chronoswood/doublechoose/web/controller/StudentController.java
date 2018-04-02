package com.chronoswood.doublechoose.web.controller;

import com.chronoswood.doublechoose.model.Result;
import com.chronoswood.doublechoose.model.Student;
import com.chronoswood.doublechoose.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/student")
@CrossOrigin("*")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping(path = "/update")
    public Result update(Student student){
        studentService.updateStudent(student);
        return Result.success(null);
    }

    @RequestMapping(path = "/detail")
    public Result detail(@Param("userName") String userName){
        return Result.success(studentService.queryStudentByUsername(userName));
    }
}
