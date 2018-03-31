package com.chronoswood.doublechoose.dao;

import com.chronoswood.doublechoose.model.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Mapper
@Repository
public interface StudentDao {
    @Select("select * from student where user_naem=#{userName} limit 1")
    Student queryStudentByUsername(String userName);

    @UpdateProvider(type = SqlProvider.class, method = "update")
    int updateStudentInfo(Student student);

    @Insert("insert into student(user_name, name, gender) values(#{userName}, #{name}, #{gender})")
    int addStudent(Student student);

    class SqlProvider{
        public static String update(Student student) {
            return new SQL() {{
                UPDATE("student");
                if (StringUtils.hasText(student.getAwards())) {
                    SET("awards", "#{awards}");
                }
                if (StringUtils.hasText(student.getInterest())) {
                    SET("interest", "#{interest}");
                }
                if (StringUtils.hasText(student.getResearchDirection())) {
                    SET("research_direction", "#{researchDirection}");
                }
                if (StringUtils.hasText(student.getIntroduction())) {
                    SET("introduction", "#{introduction}");
                }
                if (StringUtils.hasText(student.getIntroduction())) {
                    SET("introduction", "#{introduction}");
                }
                if (StringUtils.hasText(student.getPhotoURL())) {
                    SET("photoURL", "#{photoURL}");
                }
                WHERE("user_name=#{userName}");
            }}.toString();
        }
    }
}
