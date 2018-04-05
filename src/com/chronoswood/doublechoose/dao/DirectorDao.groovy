package com.chronoswood.doublechoose.dao

import com.chronoswood.doublechoose.model.Director
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface DirectorDao {

    @Insert("insert into director(user_name, gender, name, college, title) values(#{userName}, #{gender}, #{name}, #{college}, #{title})")
    int addDirector(Director director)


    @Select("select * from director where user_name=#{userName} limit 1")
    Director getDirectorByUsername(String userName);

    @Select("select * from director order by name limit #{offset}, #{amount}")
    List<Director> getDirectors(int offset, int amount);
}
