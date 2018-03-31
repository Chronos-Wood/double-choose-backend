package com.chronoswood.doublechoose.service;

import com.chronoswood.doublechoose.model.Student;

public interface StudentService {
    /**
     * 通过账号名查询学生详细信息
     * @param userName 账号名
     * @return null如果查询不到相关信息，否则返回Student实例
     */
    Student queryStudentByUsername(String userName);

    /**
     * 插入学生 仅插入学号、姓名、和性别。其余字段后续更新
     * @param student 要插入的学生
     * @return 受影响的行数 大于0成功
     */
    int addStudent(Student student);
    /**
     * 更新student的信息，这个方法会更新student中的所有非空数据项
     * @param student
     * @return 受影响的行数
     */
    int updateStudent(Student student);
}
