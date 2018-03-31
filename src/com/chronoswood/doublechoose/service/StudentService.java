package com.chronoswood.doublechoose.service;

import com.chronoswood.doublechoose.model.Student;

public interface StudentService {
    /**
     * 通过账号名查询学生详细信息
     * @param userName 账号名
     * @return null如果查询不到相关信息，否则返回Student实例
     */
    Student queryStudentByUsername(String userName);
}
