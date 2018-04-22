package com.chronoswood.doublechoose.dao

import com.chronoswood.doublechoose.model.Will
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface AssignmentDao {
    int insertOrUpdate(@Param('acceptedWills') List<Will> acceptedWills);

    @Select('select * from assignment limit #{offset}, #{amount}')
    List<Will> queryAssignment(@Param('offset') int offset, @Param('amount') int amount);
}
