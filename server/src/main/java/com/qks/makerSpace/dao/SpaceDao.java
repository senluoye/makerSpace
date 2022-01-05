package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.Audit;
import com.qks.makerSpace.entity.database.Space;
import com.qks.makerSpace.entity.database.SpacePerson;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceDao {

    @Select("select * from space where create_name = #{createName}")
    List<Space> getSpaceListByName(String createName);

    @Insert("insert into space(in_apply_id, `describe`, create_name, apply_time, " +
            "           team_number, help) " +
            "values (#{inApplyId}, #{describe}, #{createName}, #{applyTime}, " +
            "       #{teamNumber}, #{help})")
    Integer addProject(Space space);

    @Insert("insert into space_person(space_person_id, in_apply_id, department, " +
            "       person_phone, person_qq, person_wechat, note, major, person_name) " +
            "VALUES (#{spacePersonId}, #{inApplyId}, #{department}, #{personPhone}, " +
            "       #{personQq}, #{personWechat}, #{note}, #{major}, #{personName})")
    Integer addPerson(SpacePerson spacePerson);

    @Delete("delete space.*, space_person.* " +
            "from space left join space_person " +
            "on space.in_apply_id = space_person.in_apply_id " +
            "where space.in_apply_id = #{id}")
    void quitSpace(String id);

    @Insert("insert into user_space(user_id, in_apply_id) values (#{userId}, #{inApplyId})")
    Integer addUserSpace(String userId, String inApplyId);

    @Insert("insert into audit(audit_id, administrator_audit, leadership_audit, `describe`) " +
            "values (#{auditId}, #{administratorAudit}, #{leadershipAudit}, #{describe})")
    Integer addAudit(Audit audit);

    @Select("select user_id from user_space where in_apply_id = #{inApplyId}")
    String getUserIdByInApplyId(String inApplyId);

    @Select("select in_apply_id from user_space where user_id = #{userId}")
    List<String> getInApplyIdByUserId(String userId);

    @Delete("delete from audit where audit_id = #{inApplyId}")
    void deleteFromAuditByInApplyId(String inApplyId);
}
