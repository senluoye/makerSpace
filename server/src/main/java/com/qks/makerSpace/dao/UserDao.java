package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.EmailAuth;
import com.qks.makerSpace.entity.database.User;
import com.qks.makerSpace.entity.database.UserAccountApplying;
import com.qks.makerSpace.entity.response.HomePageRes;
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

//    @Insert("insert into user_account_applying(user_account_id, name, `describe`, password, submit_time) " +
//            "VALUES (#{})")
//    Integer addUserAccountApplying(UserAccountApplying userAccountApplying);

    @Select("select credit_code from user_company where user_id = #{userId}")
    List<String> getCreditCodeByUserId(String userId);

    List<String> getNameByCreditCode();

    // 获取用户主页信息
//    @Select()
    List<HomePageRes> getHomePages(String creditCode);
}
