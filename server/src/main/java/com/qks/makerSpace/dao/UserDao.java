package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.EmailAuth;
import com.qks.makerSpace.entity.database.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from user where user_id = #{userId}")
    List<User> getUserByUserId(String userId);

    @Update("update user set email = #{email} where user_id = #{userId}")
    Integer changeUserEmail(String userId, String email);

    @Update("update email_auth " +
            "set authorization_code = #{authorizationCode} " +
            "where user_id = #{userId}")
    Integer changeEmailAuth(EmailAuth emailAuth);
}
