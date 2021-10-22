package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDao {

    @Select("select * " +
            "from user " +
            "where name != #{admin}")
    List<User> getAllUser(String admin);

    @Select("select * " +
            "from user " +
            "where name = #{name} and password = #{password}")
    User getUserByNameAndPassword(String name, String password);

    @Insert("insert into user(name, password, user_id) " +
            "VALUES (#{name}, #{password}, #{id})")
    Integer addUser(String id, String name, String password);

}
