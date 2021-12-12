package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterDao {

    @Insert("insert into user (user_id, name, password, email, user_describe) " +
            "values (#{userId}, #{name}, #{password}, #{email}, #{userDescribe})")
    Integer addNewUser(User user);

    @Insert("insert into user_company(user_id) values (#{id})")
    Integer updateUserCompany(String id);
}
