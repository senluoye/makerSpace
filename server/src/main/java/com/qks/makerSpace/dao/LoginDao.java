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
            "where name = #{id} and password = #{password} and user_describe = #{role}")
    User adminOrLeaderLogin(String id, String password, int role);

    @Select("select * from user " +
            "where user_id = #{id} " +
            "and password = #{password}")
    User commonLogin(String id, String password);

    @Select("select alive from user where user_id = #{id} and password = #{password}")
    int selectAlive(String id, String password);
}
