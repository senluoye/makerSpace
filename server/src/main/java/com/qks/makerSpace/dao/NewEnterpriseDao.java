package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NewEnterpriseDao {

//  注册
    @Insert("insert into " +
            "new(new_id,credit_code,organization_code,password,name,picture,represent,represent_card,represent_phone," +
            "represent_email,agent,agent_phone,agent_email)" +
            "VALUES (#{newId},#{creditCode},#{organizationCode},#{password}," +
            "#{name},#{picture},#{represent},#{representCard}," +
            "#{representPhone},#{representEmail},#{agent}," +
            "#{agentPhone},#{agentEmail})")
    int newRegister(News news);

//  更新提交更多数据
    @Update("update new " +
            "set register_capital = #{registerCapital}, real_capital = #{realCapital}, origin_number = #{originNumber}," +
            "register_time = #{registerTime}, nature = #{nature}, certificate = #{certificate}, involved = #{involved}," +
            "main_business = #{mainBusiness}, business = #{business}, new_shareholder_id = #{newShareholderId}," +
            "new_mainperson_id = #{newMainpersonId}, new_project_id = #{newProjectId}, new_intellectual_id = #{newIntellectualId}," +
            "suggestion = #{suggestion}, note = #{note}, submit_time = {submitTime}" +
            "where new_id = #{newId}")
    Integer updateNew(News news);

    @Insert("insert into " +
            "new_shareholder(id, new_shareholder_id, name, stake, nature)" +
            "VALUES (#{id}, #{newShareholderId}, #{name}, #{stake}, #{nature})")
    int insertNewShareholder(NewShareholder newShareholder);

    @Insert("insert into new_mainperson (id, new_mainperson_id, name, born, job, school, title, background, professional)" +
            "VALUES (#{id}, #{newMainpersonId}, #{name}, #{born}, #{job}, #{school}, #{title}, #{background}, #{professional})")
    int insertNewMainPerson(NewMainPerson newMainPerson);

    @Insert("insert into new_project (id, new_project_id, project_brief, advantage, market, energy, pollution, noise, others)" +
            "VALUES (#{id}, #{newProjectId}, #{projectBrief}, #{advantage}, #{market}, #{energy}, #{pollution}, #{noise}, #{others})")
    int insertNewProject(NewProject newProject);

    @Insert(" insert into new_intellectual (id, new_intellectual_id, name, kind, apply_time, approval_time, intellectual_file) " +
            "values (#{id}, #{newIntellectualId}, #{name}, #{kind}, #{applyTime}, #{approvalTime}, #{inte" +
            "llectualFile})")
    int insertNewIntellectual(NewIntellectual newIntellectual);

//  查询数据
    @Select(" select * from new")
    List<News> getAllNew();

    @Select(" select * from new_demand where new_demand_id = #{newDemandId}")
    List<NewDemand> getNewDemandById(String newDemandId);

    @Select("select * from new_mainperson where new_mainperson_id = #{newMainPersonId}")
    List<NewMainPerson> getNewMainPerson(String newMainpersonId);

    @Select("select * from new_intellectual where new_intellectual_id = #{newIntellectualId}")
    List<NewIntellectual> getNewIntellectual(String newIntellectualId);

    @Select("select * from new_project where new_project_id = #{newProjectId}")
    List<NewProject> getNewProject(String newProjectId);

    @Select("select * from new_shareholder where new_shareholder_id = #{newShareholderId}")
    List<NewShareholder> getNewShareholder(String newShareholderId);

    @Update("update user_company set credit_code = #{id} where user_id = #{userId}")
    Integer updateUserCompany(String userId, String id);


}
