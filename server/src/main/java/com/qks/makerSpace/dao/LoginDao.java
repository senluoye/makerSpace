package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDao {

    @Select("select * from user " +
            "where name != #{admin}")
    List<User> getAllUser(String admin);

    @Select("select * from user " +
            "where name = #{name} and password = #{password} and user_describe = #{role}")
    String adminOrLeaderLogin(String name, String password, int role);

    @Select("select user_id from user " +
            "where name = #{username} " +
            "and password = #{password}")
    String commonLogin(String username, String password);

    @Select("select alive from user where name = #{username} and password = #{password}")
    int selectAlive(String username, String password);
}
