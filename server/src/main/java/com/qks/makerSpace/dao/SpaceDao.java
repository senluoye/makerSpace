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

    @Insert("insert into space_person(in_apply_id, person_name, department, major, person_phone, person_qq, person_wechat, note, in_apply_id) " +
            "VALUES (#{inApplyId}, #{personName}, #{department}, #{personName}, #{major}, #{personPhone}, #{personQq}, #{personWechat}, #{note})")
    Integer addPerson(SpacePerson spacePerson);

    @Delete("delete space.*, space_person.* " +
            "from space left join space_person " +
            "on space.in_apply_id = space_person.in_apply_id " +
            "where space.in_apply_id = #{id}")
    Integer quitSpace(String id);
}
