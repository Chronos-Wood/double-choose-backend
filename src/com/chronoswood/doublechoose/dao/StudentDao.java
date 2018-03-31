package com.chronoswood.doublechoose.dao;

import com.chronoswood.doublechoose.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentDao {
    @Select("select * from student where user_naem=#{userName} limit 1")
    Student queryStudentByUsername(String userName);

}
