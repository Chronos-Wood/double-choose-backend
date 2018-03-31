package com.chronoswood.doublechoose.dao;

import com.chronoswood.doublechoose.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentDao {
    @Select("select * from student where user_naem=#{userName} limit 1")
    Student queryStudentByUsername(String userName);

}
