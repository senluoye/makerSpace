package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.Quit;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface QuitDao {

    @Select("select user_describe from user where name = #{name}")
    int selectDescribeByName(String name);

    @Insert("insert into quit(id, name, time, room, agent, phone, reason, real_time, username, account, " +
            "opening_bank, quit_describe, admin_audit, submit_time) values (#{id}, #{name}, #{time}, " +
            "#{room}, #{agent}, #{phone}, #{reason}, #{realTime}, #{username}, #{account}, #{openingBank}, " +
            "#{quitDescribe}, #{adminAudit}, #{submitTime})")
    Integer insertQuit(Quit quit);

    @Select("select * from quit where name = #{name} order by submit_time desc limit 1")
    Quit selectQuitByName(String name);

    @Select("select * from quit where admin_audit = '待审核' group by name having max(submit_time)")
    List<Quit> getAllQuit();

    @Select("select * from quit order by submit_time desc")
    List<Quit> allQuit();

    @Update("update quit set admin_audit = '未通过'")
    Integer disagree(String id);

    //审核通过的相关DAO
    @Update("update user set alive = 2 where name = #{name}")
    Integer updateAlive(String name);

    @Update("update quit set admin_audit = '通过' where id = #{id}")
    Integer agree(String id);

    @Select("select name from quit where id = #{id}")
    String selectNameById(String id);

    @Select("select credit_code from user_company where user_id = (select user_id from user where name = #{name})")
    String getCreditCode(String name);

    @Select("select quit_describe from quit where id = #{id}")
    int getDescribe(String id);

    @Update("update new set alive = 0 where credit_code = #{creditCode}")
    Integer updateNewAlive(String creditCode);

    @Update("update old set alive = 0 where credit_code = #{creditCode}")
    Integer updateOldAlive(String creditCode);

    @Update("update form set alive = 0 where credit_code = #{creditCode}")
    Integer updateFormAlive(String creditCode);
}
