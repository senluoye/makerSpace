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

    @Insert("insert into user(name, password, user_id) " +
            "VALUES (#{name}, #{password}, #{id})")
    Integer addAdmin(String id, String name, String password);

//    迁入和独立注册企业登录
//    使用统一社会信用代码（18字符串）或者 组织机构代码
    @Select("select from old" +
            "where (credit_code = #{username} or organization_code = #{username}) and password = #{password}")
    Integer oldLogin(String username, String password);

//    新成立企业或非独立注册企业
//    使用统一社会信用代码（18字符串）或者 组织机构代码
    @Select("select from new" +
            "where (credit_code = #{username} or organization_code = #{username}) and password = #{password}")
    Integer newLogin(String username, String password);
}
