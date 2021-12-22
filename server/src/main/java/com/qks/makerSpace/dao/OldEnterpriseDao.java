package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.response.FormDetails;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OldEnterpriseDao {

    @Insert("insert into old(old_id, credit_code, organization_code, name, password, " +
            "represent, represent_phone, represent_email, agent, agent_phone, agent_email)" +
            "VALUES (#{oldId}, #{creditCode}, #{organizationCode}, #{name}, #{password}, #{represent}, " +
            "#{representPhone}, #{representEmail}, #{agent}, #{agentPhone}, #{agentEmail})")
    Integer oldRegister(Old old);

    @Update("update old set organization_code = #{organizationCode}, name = #{name}, password = #{password}, represent = #{represent}, " +
            "represent_phone = #{representPhone}, represent_email = #{representEmail}, agent = #{agent}, agent_phone = #{agentPhone}, " +
            "agent_email = #{agentEmail} " +
            "where credit_code = #{creditCode}")
    Integer updateOldRegister(Old old);

    @Select("select * from old where credit_code = #{creditCode}")
    Old exit(String creditCode);

    @Select("select * from old")
    List<Old> getAllOld();

    @Update("update old " +
            "set register_address = #{registerAddress}, license = #{license}, register_capital = #{registerCapital}," +
            "  real_address = #{realAddress}, real_capital = #{realCapital}, last_income = #{lastIncome}," +
            "  last_tax = #{lastTax}, employees = #{employees}, origin_number = #{originNumber}," +
            "  set_date = #{setDate}, nature = #{nature}, certificate = #{certificate}, involved = #{involved}," +
            "  main_business = #{mainBusiness}, way = #{way}, business = #{business}, " +
            "  old_shareholder_id = #{oldShareholderId}, old_mainperson_id = #{oldMainpersonId}, " +
            "  old_project_id = #{oldProjectId}, old_intellectual_id = #{oldIntellectualId}," +
            "  old_funding_id = #{oldFundingId}, cooperation = #{cooperation}, suggestion = #{suggestion}, note = #{note}, submit_time = #{submitTime} " +
            "where credit_code = #{oldId}")
    Integer updateOld(Old old);

    @Update("update old set state = #{state}, submit_time = #{submitTime}, room = #{room}, old_demand_id = #{oldDemandId} " +
            "where credit_code = #{creditCode}")
    Integer updateOldForDemand(String creditCode, String state, String submitTime, String room, String oldDemandId);

    /**
     * 以下是关于插入Old相关子表的操作
     */
    @Insert("insert into old_mainperson (id, name, born, job, school, title, background, professional, old_mainperson_id)" +
            "values (#{id}, #{name}, #{born}, #{job}, #{school}, #{title}, #{background}, #{professional}, #{oldMainpersonId})")
    Integer insertOldMainPeople(OldMainPerson oldMainPerson);

    @Insert("insert into old_project (id, project_brief, advantage, market, energy, pollution, noise, others, old_project_id) " +
            "values (#{id}, #{projectBrief}, #{advantage}, #{market}, #{energy}, #{pollution}, #{noise}, #{others}, #{oldProjectId});")
    Integer insertOldProjects(OldProject oldProject);

    @Insert("insert into old_intellectual (id, name, kind, apply_time, approval_time, intellectual_file, old_intellectual_id) " +
            "values (#{id}, #{name}, #{kind}, #{applyTime}, #{approvalTime}, #{intellectualFile}, #{oldIntellectualId});")
    Integer insertOldIntellects(OldIntellectual oldIntellectual);

    @Insert("insert into old_funding(id, name, level, time, grants, award, funding_id) " +
            "VALUES (#{id}, #{name}, #{level}, #{time}, #{grants}, #{award}, #{fundingId})")
    Integer insertOldFundings(OldFunding oldFunding);

    @Insert("insert into old_shareholder(id, name, stake, nature, old_shareholder_id) " +
            "VALUES (#{id}, #{name}, #{stake}, #{nature}, #{oldShareholderId})")
    Integer insertOldShareholder(OldShareholder oldShareholder);

    @Insert("insert into old_demand(lease_area, position, lease, floor, electric, water, web, others, old_demand_id) " +
            "VALUES (#{leaseArea}, #{position}, #{lease}, #{floor}, #{electric}, #{water}, #{web}, #{others}, #{oldDemandId})")
    Integer addOldDemand(OldDemand oldDemand);

    @Select("select old_demand_id from old where credit_code = #{creditCode}")
    String[] selectDemandByCreditCode(String creditCode);

    @Update("update old_demand " +
            "set lease_area = #{leaseArea}, position = #{position}, lease = #{lease}, " +
            "floor = #{floor}, electric = #{electric}, water = #{water}, web = #{web}, others = #{others} " +
            "where old_demand.old_demand_id = #{oldDemandId}")
    Integer updateOldDemand(OldDemand oldDemand, String oldDemandId);

    @Select("select old_demand_id from old where credit_code = #{creditCode}")
    String selectOldDemandIdByCreditCode(String creditCode);

    @Update("update old set old_demand_id = #{oldDemandId} where credit_code = #{creditCode}")
    Integer updateOldDemandId(String creditCode, String oldDemandId);

    /**
     * 以下是关于查询Old相关子表的操作
     */
    @Select("select * from old_demand where old_demand_id = #{id}")
    List<OldDemand> getOldDemandById(String id);

    @Select("select * from old_mainperson where old_mainperson_id = #{id}")
    List<OldMainPerson>  getOldMainPeopleById(String id);

    @Select("select * from old_project where old_project_id = #{id}")
    List<OldProject> getOldProjectById(String id);

    @Select("select * from old_funding where funding_id = #{id}")
    List<OldFunding> getOldFundingById(String id);

    @Select("select * from old_shareholder where old_shareholder_id = #{id}")
    List<OldShareholder> getOldShareholderById(String id);

    @Select("select * from old_intellectual where old_intellectual_id = #{id}")
    List<OldIntellectual> getOldIntellectualById(String id);

    @Select("select user_id from user_company where credit_code = #{creditCode}")
    String selectUserCompany(String creditCode);

    @Select("select credit_code from user_company where user_id = #{userId}")
    String selectCreditCodeByUserId(String userId);

    @Select("select old_demand_id from old where credit_code = #{creditCode}")
    String demandExit(String creditCode);

    // 其他子表的相关操作
    @Update("update user_company set credit_code = #{creditCode} where user_id = #{userId}")
    Integer updateUserCompany(String userId, String creditCode);

    @Insert("insert into user_company(user_id,credit_code) " +
            "values (#{userId},#{creditCode})")
    Integer insertUserCompany(String userId, String creditCode);

    @Insert("insert into audit(audit_id, administrator_audit, leadership_audit) " +
            "values (#{auditId}, #{administratorAudit}, #{leadershipAudit})")
    Integer insertAudit(Audit audit);

    @Select("select * from form where credit_code = #{creditCode}")
    List<FormDetails> getAllFormDetails(String creditCode);

    @Select("select * from audit where audit_id = #{creditCode}")
    List<Audit> getAudit(String creditCode);

    // 下面是已经填过表的
    @Delete("delete from old_mainperson where old_mainperson_id = #{id}")
    Integer deleteOldMainPerson(String id);

    @Delete("delete from old_project where old_project_id = #{id}")
    Integer deleteOldProject(String id);

    @Delete("delete from old_intellectual where old_intellectual_id = #{id}")
    Integer deleteOldIntellectual(String id);

    @Delete("delete from old_funding where funding_id = #{id}")
    Integer deleteOldFunding(String id);

    @Delete("delete from old_shareholder where old_shareholder_id = #{id}")
    Integer deleteOldShareholder(String id);

    @Update("update audit set administrator_audit = #{administratorAudit}, leadership_audit = #{leadershipAudit} " +
            "where audit_id = #{auditId}")
    Integer updateAudit(Audit audit);

    @Select("select old_mainperson_id from old where credit_code = #{creditCode}")
    String selectOldMainPerson(String creditCode);

    @Select("select old_project_id from old where credit_code = #{creditCode}")
    String selectOldProject(String creditCode);

    @Select("select old_intellectual_id from old where credit_code = #{creditCode}")
    String selectOldIntellectual(String creditCode);

    @Select("select old_funding_id from old where credit_code = #{creditCode}")
    String selectOldFunding(String creditCode);

    @Select("select old_shareholder_id from old where credit_code = #{creditCode}")
    String selectOldShareholder(String creditCode);

    @Select("select old_mainperson_id from old where credit_code = #{creditCode}")
    String exitMainPerson(String creditCode);
}
