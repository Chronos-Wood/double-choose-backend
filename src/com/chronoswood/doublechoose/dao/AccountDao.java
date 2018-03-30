package com.chronoswood.doublechoose.dao;

import com.chronoswood.doublechoose.model.AccountDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountDao {

    @Insert("insert into account(id, user_name, password, salt, role, create_time) " +
            "values(#{id}, #{userName}, #{password}, #{salt}, #{role}, #{createTime})")
    int register(AccountDO account);

    @Select("select * from account where user_name=#{userName}")
    AccountDO getUserByUsername(String userName);
}
