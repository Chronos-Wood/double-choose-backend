package com.chronoswood.doublechoose.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import com.chronoswood.doublechoose.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentDao {
    @Select("select * from student where user_name=#{userName} limit 1")
    Student queryStudentByUsername(String userName)

    @UpdateProvider(type=UpdateStudentInfo, method='provide')
    int updateStudentInfo(Student student)

    @Insert("insert into student(user_name, name, gender) values(#{userName}, #{name}, #{gender})")
    int addStudent(Student student);

    @Select("select * from director order by name limit #{offset}, #{amount}")
    List<Student> listStudents(@Param("offset") int offset, @Param("amount") int amount);
}

class UpdateStudentInfo {
    static String provide(Student student){
        new SQL(){{
            UPDATE('student')
            if((student.userName?:'') != ''){
                SET("user_name='$student.userName'")
            }
            if((student.gender?:-1) != (-1)){
                SET("gender=$student.gender")
            }
            if((student.photoURL?:'') != ''){
                SET("photo_url='$student.photoURL'")
            }
            if((student.introduction?:'') != ''){
                SET("introduction='$student.introduction'")
            }
            if((student.interest?:'') != ''){
                SET("interest='$student.interest'")
            }
            if((student.awards?:'') != ''){
                SET("awards='$student.awards'")
            }
            if((student.researchDirection?:'') != ''){
                SET("research_direction='$student.researchDirection'")
            }
            WHERE("user_name='$student.userName'")
        }}.toString()
    }
}
