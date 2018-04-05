package com.chronoswood.doublechoose.web.controller;

import com.chronoswood.doublechoose.model.Message;
import com.chronoswood.doublechoose.model.Result;
import com.chronoswood.doublechoose.model.Student;
import com.chronoswood.doublechoose.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/list")
    public Result<?> list(@RequestParam("offset") int offset, @RequestParam("amount") int amount){
        if(offset<0||amount<0){
            return new Result<>(Message.BIND_ERROR, null);
        }
        return new Result<>(Message.SUCCESS, studentService.listStudents(offset, amount));
    }
}
