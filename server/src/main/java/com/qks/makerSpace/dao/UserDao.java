package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.*;
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

    @Select("select * from user_company where user_id = #{userId}")
    List<UserCompany> getUserCompanyByUserId(String userId);

    @Select("select * " +
            "from new " +
            "where credit_code = #{creditCode} " +
            "and submit_time = (select max(submit_time) from new where credit_code = #{creditCode})")
    News getLastNewByCreditCode(String creditCode);

    @Select("select * from new_demand where new_demand_id = #{newDemandId}")
    NewDemand getLastNewDemandById(String newDemandId);

    @Select("select * " +
            "from old " +
            "where credit_code = #{creditCode} " +
            "and submit_time = (select max(submit_time) from old where credit_code = #{creditCode})")
    Old getLastOldByCreditCode(String creditCode);

    @Select("select * from old_demand where old_demand_id = #{oldDemandId}")
    OldDemand getLastOldDemandById(String oldDemandId);

    @Select("select * " +
            "from space" +
            " where in_apply_id = #{id} " +
            "and submit_time = (select max(submit_time) from space where in_apply_id = #{id})")
    Space getLastSpaceById(String id);

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
