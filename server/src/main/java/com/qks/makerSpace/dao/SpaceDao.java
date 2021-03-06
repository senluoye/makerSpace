package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.Audit;
import com.qks.makerSpace.entity.database.Space;
import com.qks.makerSpace.entity.database.SpacePerson;
import com.qks.makerSpace.entity.database.UserSpace;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface SpaceDao {

    @Select("select * from space where create_name = #{createName}")
    List<Space> selectSpaceByName(String createName);

    @Select("select * from user_space where in_apply_id = #{inApplyId}")
    List<UserSpace> selectUserSpaceById(String inApplyId);

    @Select("select in_apply_id from user_space where user_id = #{userId}")
    List<String> selectUserSpaceByUserId(String userId);


    @Insert("insert into space(space_id, in_apply_id, create_name, apply_time, team_number, " +
            "`describe`, help, submit_time, time, accepter, office_opinion, " +
            "leader_opinion) " +
            "values (#{spaceId}, #{inApplyId}, #{createName}, #{applyTime}, #{teamNumber}, " +
            "#{describe}, #{help}, #{submitTime}, #{time}, #{accepter}, #{officeOpinion}, " +
            "#{leaderOpinion})")
    Integer addProject(Space space);

    @Update("update user_space set in_apply_id = #{inApplyId} where user_id = #{userId}")
    Integer updateUserSpace(UserSpace userSpace);

    @Insert("insert into space_person(space_person_id, in_apply_id, department, " +
            "       person_phone, person_qq, person_wechat, note, major, person_name, submit_time) " +
            "VALUES (#{spacePersonId}, #{inApplyId}, #{department}, #{personPhone}, " +
            "       #{personQq}, #{personWechat}, #{note}, #{major}, #{personName}, #{submitTime})")
    Integer addPerson(SpacePerson spacePerson);

    @Delete("delete space.*, space_person.* " +
            "from space left join space_person " +
            "on space.in_apply_id = space_person.in_apply_id " +
            "where space.in_apply_id = #{id}")
    void quitSpace(String id);

    @Insert("insert into user_space(user_id, in_apply_id) values (#{userId}, #{inApplyId})")
    Integer addUserSpace(String userId, String inApplyId);

    @Insert("insert into audit(audit_id, administrator_audit, " +
            "leadership_audit, `describe`, submit_time, credit_code) " +
            "values (#{auditId}, #{administratorAudit}, #{leadershipAudit}, " +
            "#{describe}, #{submitTime}, #{creditCode})")
    Integer addAudit(Audit audit);

    @Select("select user_id from user_space where in_apply_id = #{inApplyId}")
    String getUserIdByInApplyId(String inApplyId);

    @Select("select in_apply_id from user_space where user_id = #{userId}")
    List<String> getInApplyIdByUserId(String userId);

    @Delete("delete from audit where audit_id = #{inApplyId}")
    void deleteFromAuditByInApplyId(String inApplyId);
}
