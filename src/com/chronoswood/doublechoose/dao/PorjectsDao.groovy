package com.chronoswood.doublechoose.dao

import com.chronoswood.doublechoose.model.Director
import com.chronoswood.doublechoose.model.Project;
import com.chronoswood.doublechoose.model.Projects
import com.chronoswood.doublechoose.model.Student
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.One
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.jdbc.SQL
import org.springframework.stereotype.Repository


@Mapper
@Repository
interface ProjectsDao {
    @Select('select * from project limit #{offset}, #{amount}')
    @Results(
            @Result(property = "director", column = "director_id", javaType=Director.class, one = @One(select = "getDirectorById"))
    )
    List<Project> queryProjects(@Param('offset') int offset, @Param('amount') int amount)

    @Select("select * from director where id = #{id} limit 1")
    Director getDirectorById(Integer id);

    @Select("select * from project where name=#{Name} limit 1")
    Project queryProjectByName(String Name)

    @Select("select * from project where id=#{id} limit 1")
    Project queryProjectById(String projectId)

    @UpdateProvider(type = UpdateProjectInfo, method = 'provide')
    int updateProjectInfo(Project project)

    @Insert("insert into project(name, preview_image, description, director_id, period_id, participant_amount, begin, end) values(#{name}, #{preview_image}, #{description}, #{director_id}, #{period_id}, #{participantAmount}, #{begin}, #{end})")
    int addProject(Project project);

    @Select('select * from project join director on project.director_id=director.id where director.user_name=#{directorUserName}')
    List<Project> queryProjectsByDirectorUserName(@Param('directorUserName') String directorUserName,@Param('limit') int limit,@Param('amount') int amount);

    class UpdateProjectInfo {
        static String provide(Project project) {
            new SQL() {
                {
                    UPDATE('project')
                    if ((project.name ?: '') == '') {
                        SET("name='$project.name'")
                    }
                    if ((project.preview_image ?: '') == '') {
                        SET("preview_image='$project.preview_image'")
                    }
                    if ((project.description ?: '') == '') {
                        SET("description='$project.description'")
                    }
                    if ((project.director_id ?: '') == '') {
                        SET("director_id='$project.director_id'")
                    }
                    if ((project.period_id ?: '') == '') {
                        SET( "period_id='$project.period_id'")
                    }
                    if((project.participantAmount?:0) == 0){
                        SET("participant_amount='$project.participantAmount'")
                    }
                    if ((project.begin ?: '') == '') {
                        SET("begin='$project.begin'")
                    }
                    if ((project.end ?: '')=='') {
                        SET("end='$project.end'")
                    }
                    WHERE("id='$project.id'")
                }
            }.toString()
        }
    }
}