package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDao {

    @Select("select * from user " +
            "where name != #{admin}")
    List<User> getAllUser(String admin);

    @Select("select * from user " +
            "where name = #{name} and password = #{password}")
    User AdminOrLeaderLogin(String name, String password);

    @Select("select user_id from user " +
            "where name = #{username} " +
            "and password = #{password}")
    String commonLogin(String username, String password);

}
