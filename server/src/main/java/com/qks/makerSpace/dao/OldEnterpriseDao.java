package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OldEnterpriseDao {

    @Select("insert into old(old_id, credit_code, organization_code, name, password, " +
            "represent, represent_phone, register_address, represent_email, agent, agent_phone, agent_email )" +
            "VALUES (#{oldId}, #{creditCode}, #{organizationCode}, #{name}, #{password}, #{represent}, " +
            "#{representPhone}, #{registerAddress}, #{representEmail}, #{agent}, #{agentPhone}, #{agentEmail})")
    Integer oldRegister(Old old);

    @Select("")
    List<Old> getAllOld();

    @Update("update old " +
            "set register_address = #{registerAddress}, license = #{license}, register_capital = #{registerCapital}," +
            "   real_address = #{realAddress}, real_capital = #{realCapital}, last_income = #{lastIncome}," +
            "   last_tax = #{lastTax}, employees = #{employees}, origin_number = #{originNumber}," +
            "   set_date = #{setDate}, nature = #{nature}, certificate = #{certificate}, involved = #{involved}," +
            "   main_business = #{mainBusiness}, way = #{way}, business = #{business}, " +
            "   old_shareholder_id = #{oldShareholderId}, old_mainperson_id = #{oldMainpersonId}, " +
            "   old_project_id = #{oldProjectId}, old_intellectual_id = #{oldIntellectualId}," +
            "   old_funding_id = #{oldFundingId}, cooperation = #{cooperation}, suggestion = #{suggestion}, note = #{note}" +
            "where old_id = #{oldId}")
    Integer updateOld(Old old);

    @Insert("insert into old_mainperson (id, name, born, job, school, title, background, professional, old_mainperson_id)" +
            "values (#{id}, #{oldMainpersonId}, #{name}, #{born}, #{job}, #{school}, #{title}, #{background}, #{professional})")
    void insertOldMainPeople(OldMainPerson oldMainPerson);


    @Insert("insert into old_project (id, project_brief, advantage, market, energy, pollution, noise, others, old_project_id) " +
            "values (#{id}, #{project_brief}, #{advantage}, #{market}, #{energy}, #{pollution}, #{noise}, #{others}, #{oldProjectId});")
    void insertOldProjects(OldProject oldProject);

    @Insert(" insert into old_intellectual (id, name, kind, apply_time, approval_time, intellectual_file, old_intellectual_id) " +
            "values (#{id}, #{name}, #{kind}, #{applyTime}, #{approvalTime}, #{intellectualFile}, #{oldIntellectualId});")
    void insertOldIntellects(OldIntellectual oldIntellectual);

    @Insert("insert into old_funding(id, name, level, time, grants, award, funding_id) " +
            "VALUES (#{id}, #{name}, #{level}, #{time}, #{grants}, #{award}, #{fundingId})")
    void insertOldFundings(OldFunding oldFunding);

    @Insert("insert into old_shareholder(id, name, stake, nature, old_shareholder_id) " +
            "VALUES (#{id}, #{name}, #{stake}, #{nature}, #{oldShareholderId})")
    void insertOldShareholder(OldShareholder oldShareholder);

    List<OldDemand> getOldDemandById(String id);
}
