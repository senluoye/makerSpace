package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.Space;
import com.qks.makerSpace.entity.database.SpacePerson;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceDao {

    @Insert("insert into space(in_apply_id, `describe`, create_name, apply_time, " +
            "           team_number, help, administrator_audit, leadership_audit) " +
            "values (#{inApplyId}, #{describe}, #{createName}, #{applyTime}, " +
            "       #{teamNumber}, #{help}, #{administratorAudit}, #{leadershipAudit})")
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
    Integer quitSpace(String id);
}
