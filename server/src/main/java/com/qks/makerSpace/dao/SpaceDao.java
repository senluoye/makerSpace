package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.Space;
import com.qks.makerSpace.entity.SpacePerson;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceDao {

    @Insert("insert into space(in_apply_id, `describe`, create_name, apply_time, team_number, brief, help) " +
            "values (#{inApplyId}, #{describe}, #{createName}, #{applyTime}, #{teamNumber}, #{brief}, #{help})")
    Integer addProject(Space space);

    @Insert("insert into space_person(person_name, department, major, person_phone, person_qq, person_wechat, note, in_apply_id) " +
            "VALUES (#{personName}, #{department}, #{personName}, #{major}, #{personPhone}, #{personQq}, #{personWechat}, #{note})")
    Integer addPerson(SpacePerson spacePerson);

    @Delete("delete space.*, space_person.* " +
            "from space, space_person " +
            "where ")
    Integer quitSpace(String id);
}
