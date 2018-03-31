package com.chronoswood.doublechoose.dao;

import com.chronoswood.doublechoose.model.Student
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Mapper
@Repository
public interface StudentDao {
    @Select("select * from student where user_naem=#{userName} limit 1")
    Student queryStudentByUsername(String userName)

    @UpdateProvider(type=UpdateStudentInfo, method='provide')
    int updateStudentInfo(Student student)

    @Insert("insert into student(user_name, name, gender) values(#{userName}, #{name}, #{gender})")
    int addStudent(Student student);
}

class UpdateStudentInfo {
    static String provide(Student student){
        new SQL(){{
            UPDATE('student')
            if(student.userName?:'' != ''){
                SET("user_name=$student.userName")
            }
            if(student.gender!=null){
                SET("gender=$student.gender")
            }
            if(student.photoURL?:'' != ''){
                SET("photo_url=$student.photoURL")
            }
            if(student.introduction?:'' != ''){
                SET("introduction=$student.introduction")
            }
            if(student.interest?:'' != ''){
                SET("interest=$student.interest")
            }
            if(student.awards?:'' != ''){
                SET("awards=$student.awards")
            }
            if(student.researchDirection?:'' != ''){
                SET("research_direction=$student.researchDirection")
            }
            WHERE("user_name=$student.userName")
        }}.toString()
    }
}
