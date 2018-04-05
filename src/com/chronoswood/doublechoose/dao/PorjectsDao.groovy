package com.chronoswood.doublechoose.dao

import com.chronoswood.doublechoose.model.Project;
import com.chronoswood.doublechoose.model.Projects
import com.chronoswood.doublechoose.model.Student
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.jdbc.SQL
import org.springframework.stereotype.Repository

import java.time.LocalDateTime;

@Mapper
@Repository
public interface ProjectsDao {
    @Select("select * from project")
    Projects queryProjects()

    @Select("select * from project where name=#{Name} limit 1")
    Project queryProjectByName(String Name)

    @UpdateProvider(type=UpdateProjectInfo, method='provide')
    int updateProjectInfo(Project project)

    @Insert("insert into project(name, preview_image, description, director_id, period_id, begin, end) values(#{name}, #{preview_image}, #{description}, #{director_id}, #{period_id}, #{begin}, #{end})")
    int addProject(Project project);
}

class UpdateProjectInfo {
    static String provide(Project project){
        new SQL(){{
            UPDATE('project')
            SET("name='$project.name'")
            SET("preview_image='$project.preview_image'")
            SET( sets:"description='$project.description'")
            SET( sets:"director_id='$project.director_id'")
            SET( sets:"period_id='$project.period_id'")
            SET( sets:"begin='$project.begin'")
            SET( sets:"end='$project.end'")
        }}.toString()
    }
}
