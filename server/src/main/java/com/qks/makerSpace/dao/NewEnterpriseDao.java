package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface NewEnterpriseDao {

    @Insert("insert into " +
            "new(new_id, credit_code,organization_code,password,name,picture,represent,represent_card,represent_phone," +
            "represent_email,agent,agent_phone,agent_email)" +
            "VALUES (#{credit_code},#{organization_code},#{password}," +
            "#{name},#{picture},#{represent},#{represent_card}," +
            "#{represent_phone},#{represent_email},#{agent}," +
            "#{agent_phone},#{agent_email})")
    int newRegister(News news);

    @Update("update news" +
            "set register_capital = #{registerCapital}, real_capital = #{realCapital}, origin_number = #{originNumber}," +
            "register_time = #{registerTime}, nature = #{nature}, certificate = #{certificate}, involved = #{involved}," +
            "main_business = #{mainBusiness}, business = #{business}, new_shareholder_id = #{newShareholderId}," +
            "new_mainperson_id = #{newMainpersonId}, new_project_id = #{newProjectId}, new_intellectual_id = #{newIntellectualId}," +
            "suggestion = #{suggestion}, note = #{note}, submit_time = {submitTime}" +
            "where new_id = #{newId}")
    Integer updateNew(News news);

    @Insert("insert into" +
            "new_shareholder(id, new_shareholder_id, name, stake, nature)" +
            "VALUES (#{id}, #{newShareholder}, #{name}, #{stake}, #{nature})")
    void insertNewShareholder(NewShareholder newShareholder);

    @Insert("insert into old_mainperson (id, new_mainperson_id, name, born, job, school, title, background, professional)" +
            "VALUES (#{id}, #{newMainpersonId}, #{name}, #{born}, #{job}, #{school}, #{title}, #{background}, #{professional})")
    void insertNewMainPerson(NewMainPerson newMainPerson);

    @Insert("insert into new_project (id, new_project_id, project_brief, advantage, market, energy, pollution, noise, others)" +
            "VALUES (#{id}, #{newProjectId}, #{project_brief}, #{advantage}, #{market}, #{energy}, #{pollution}, #{noise}, #{others})")
    void insertNewProject(NewProject newProject);

    @Insert(" insert into new_intellectual (id, new_intellectual_id, name, kind, apply_time, approval_time, intellectual_file) " +
            "values (#{id}, #{newIntellectualId}, #{name}, #{kind}, #{applyTime}, #{approvalTime}, #{intellectualFile})")
    void insertNewIntellectual(NewIntellectual newIntellectual);



}
