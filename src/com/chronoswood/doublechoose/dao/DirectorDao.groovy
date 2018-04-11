package com.chronoswood.doublechoose.dao

import com.chronoswood.doublechoose.model.Director
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.One
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.jdbc.SQL
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface DirectorDao {

    @Insert("insert into director(user_name, gender, name, college, title) values(#{userName}, #{gender}, #{name}, #{college}, #{title})")
    int addDirector(Director director)


    @Select("select * from director where user_name=#{userName} limit 1")
    Director getDirectorByUsername(String userName);

    @Select("select * from director order by name limit #{offset}, #{amount}")
    List<Director> getDirectors(@Param('offset') int offset,@Param('amount') int amount);

    @UpdateProvider(type=UpdateDirectorInfo, method='provide')
    int updateDirector(Director director)

    @Select("select * from director where id = #{id} limit 1")
    Director getDirectorById(Integer id);
}
class UpdateDirectorInfo{
    String provide(Director director){
        return new SQL(){{
            UPDATE('director')
            if((director.gender?:-1)!=-1){
                SET("gender='$director.gender'")
            }
            if((director.name?:'')!=''){
                SET("name='$director.name'")
            }
            if((director.photoURL?:'')!=''){
                SET("photho_url='$director.photoURL'")
            }
            if((director.introduction?:'')!=''){
                SET("introduction='$director.introduction'")
            }
            if((director.awards?:'')!=''){
                SET("awards='$director.awards'")
            }
            if((director.researchDirection?:'')!=''){
                SET("research_direction='$director.researchDirection'")
            }
            if((director.college?:'')!=''){
                SET("college='$director.college'")
            }
            if((director.tel?:'')!=''){
                SET("tel='$director.tel'")
            }
            if((director.email?:'')!=''){
                SET("email='$director.email'")
            }
            if((director.title?:'')!=''){
                SET("title='$director.title'")
            }
            WHERE("user_name='$director.userName'")
        }}.toString()
    }
}
