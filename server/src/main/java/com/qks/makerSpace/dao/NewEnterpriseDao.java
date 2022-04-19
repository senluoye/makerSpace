package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.response.Demand;
import com.qks.makerSpace.entity.response.FormDetails;
import com.qks.makerSpace.entity.response.TechnologyApplyingRes;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NewEnterpriseDao {

    @Insert("insert into new_demand(id, lease_area, position, lease, " +
            "floor, electric, water, web, others, new_demand_id, time) " +
            "VALUES (#{id}, #{leaseArea}, #{position}, #{lease}, #{floor}, " +
            "#{electric}, #{water}, #{web}, #{others}, #{newDemandId}, #{time})")
    Integer addNewDemand(NewDemand newDemand);

    @Insert("insert into " +
            "new_shareholder(id, new_shareholder_id, name, stake, nature) " +
            "VALUES (#{id}, #{newShareholderId}, #{name}, #{stake}, #{nature})")
    Integer insertNewShareholder(NewShareholder newShareholder);

    @Insert("insert into new_mainperson (id, new_mainperson_id, name, born, job, school, title, background, professional)" +
            "VALUES (#{id}, #{newMainpersonId}, #{name}, #{born}, #{job}, #{school}, #{title}, #{background}, #{professional})")
    Integer insertNewMainPerson(NewMainPerson newMainPerson);

    @Insert("insert into new_project (id, new_project_id, project_brief, advantage, market, energy, pollution, noise, others)" +
            "VALUES (#{id}, #{newProjectId}, #{projectBrief}, #{advantage}, #{market}, #{energy}, #{pollution}, #{noise}, #{others})")
    Integer insertNewProject(NewProject newProject);

    @Insert(" insert into new_intellectual (id, new_intellectual_id, name, kind, apply_time, approval_time, intellectual_file) " +
            "values (#{id}, #{newIntellectualId}, #{name}, #{kind}, #{applyTime}, #{approvalTime}, #{intellectualFile})")
    Integer insertNewIntellectual(NewIntellectual newIntellectual);

    @Select("select credit_code from user_company where user_id = #{userId}")
    List<String> selectCreditCodeByUserId(String userId);

    @Select("select * from form where credit_code = #{creditCode}")
    List<FormDetails> getAllFormDetails(String creditCode);

    @Select("select new_demand_id  from new where credit_code = #{creditCode}")
    String demandExit(String creditCode);

    @Select("select * from user where user_id = #{userId}")
    User getUserByUserId(String userId);

    @Select("select user_id from user_company where credit_code = #{creditCode}")
    List<String> selectUserIdByCreditCode(String creditCode);

    @Select("select * from user_company where user_id = #{userId}")
    List<UserCompany> selectUserCompany(String userId);

    @Select("select audit_id from audit where credit_code = #{creditCode}")
    List<String> selectAuditIdByCreditCode(String creditCode);

    @Select("select new_id from new where credit_code = #{creditCode}")
    List<String> selectNewIdByCreditCode(String creditCode);

    @Update("update new set credit_code = #{creditCode} where new_id = #{newId}")
    void updateNewCreditCode(String newId, String creditCode);

    @Update("update audit set credit_code = #{creditCode} where audit_id = #{auditId}")
    void updateAuditCreditCode(String auditId, String creditCode);

    @Update("update user_company " +
            "set credit_code = #{creditCode} " +
            "where user_id = #{userId}")
    Integer updateUserCompany(String userId, String creditCode);

    @Insert("insert into user_company(user_id,credit_code) " +
            "values (#{userId},#{creditCode})")
    void insertUserCompany(String userId, String creditCode);

    @Insert("insert into " +
            "new(new_id, credit_code, name, picture, represent, represent_card, represent_person, represent_phone," +
            "represent_email, agent, agent_phone, agent_email, register_capital, real_capital, origin_number," +
            "register_time, nature, certificate, involved, main_business, business, new_demand_id, new_shareholder_id, new_mainperson_id," +
            "new_project_id, new_intellectual_id, cooperation, suggestion, note, state, submit_time) " +
            "VALUES (#{newId}, #{creditCode}, #{name}, #{picture}, #{represent}, #{representCard}," +
            "#{representPerson}, #{representPhone}, #{representEmail}, #{agent}, #{agentPhone}," +
            "#{agentEmail}, #{registerCapital}, #{realCapital}, #{originNumber}, #{registerTime}, " +
            "#{nature}, #{certificate}, #{involved}, #{mainBusiness}, #{business}, #{newDemandId}, #{newShareholderId}, " +
            "#{newMainpersonId}, #{newProjectId}, #{newIntellectualId}, #{cooperation}, #{suggestion}, #{note}, #{state}, #{submitTime})")
    Integer insertNew(News news);

    @Insert("insert into audit(audit_id, administrator_audit, leadership_audit, `describe`, submit_time, credit_code) " +
            "values (#{auditId}, #{administratorAudit}, #{leadershipAudit}, #{describe}, #{submitTime}, #{creditCode})")
    Integer insertAudit(Audit audit);

    @Select("select administrator_audit, leadership_audit, submit_time " +
            "from audit where credit_code = #{creditCode} " +
            "order by submit_time desc")
    List<TechnologyApplyingRes> selectAuditByCreditCode(String creditCode);

    @Select("select name from new where credit_code = #{creditCode} order by submit_time desc")
    List<String> selectNewNameByCredit(String creditCode);

    @Select("select suggestion from new where credit_code = #{creditCode} order by submit_time desc")
    List<String> getSuggestionByCreditCode(String creditCode);

    @Select("select new_id from new where credit_code = #{creditCode} order by submit_time desc")
    List<String> getNewIdByCreditCode(String creditCode);

    @Select("select * from new where new_id = #{id}")
    News getNew(String id);

    @Select("select * from new_demand where new_demand_id = #{id}")
    List<NewDemand> getNewDemandById(String id);

    @Select("select * from new_mainperson where new_mainperson_id = #{id}")
    List<NewMainPerson> getNewMainPeopleById(String id);

    @Select("select * from new_project where new_project_id = #{id}")
    List<NewProject> getNewProjectById(String id);

    @Select("select * from new_intellectual where new_intellectual_id = #{id}")
    List<NewIntellectual> getNewIntellectualById(String id);

    @Select("select * from new_shareholder where new_shareholder_id = #{id}")
    List<NewShareholder> getNewShareholderById(String id);

    @Select("select new_id from new where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String getNewIdByCreditCodeAndTime(String creditCode, String submitTime);

    @Select("select name from new where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String getNewNameByCreditCodeAndTime(String creditCode, String submitTime);

    @Select("select suggestion from new where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String getNewSuggestionByCreditCodeAndTime(String creditCode, String submitTime);

    @Select("select note from new where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String getNewNoteByCreditCodeAndTime(String creditCode, String submitTime);

    @Select("select new_id from new where credit_code = #{creditCode}")
    List<String> getNewIdList(String creditCode);

    @Insert("insert into contract " +
            "set contract_id = #{contractId}, credit_code = #{creditCode}, " +
            "voucher = #{voucher}, submit_time = #{submitTime}")
    Integer addContract(Contract contract);

    @Select("select * from contract where credit_code = #{creditCode}")
    List<Contract> getNewContractList(String creditCode);

    @Select("select * from contract " +
            "where credit_code = #{creditCode} and year = #{year} and quarter = #{quarter} and `describe` = #{describe}")
    Contract getContractByCreditCodeAndQuarter(String creditCode, int year, int quarter, String describe);

    @Select("select * " +
            "from new_demand " +
            "where new_demand_id = (" +
            "   select new_demand_id " +
            "   from new " +
            "   where credit_code = #{creditCode} " +
            "   and submit_time = (" +
            "       select max(submit_time) " +
            "       from new " +
            "       where credit_code = #{creditCode}" +
            "   )" +
            ")")
    List<NewDemand> getLastNewDemandByCreditCode(String creditCode);

    News getLastNewByCreditCode(String s);

    @Update("update new_demand set lease = #{lease}, lease_area = #{leaseArea}, position = #{position}, " +
            "floor = #{floor}, electric = #{electric}, water = #{water}, web = #{web}, " +
            "others = #{others}, time = #{time} " +
            "where new_demand_id = #{newDemandId}")
    Integer updateNewDemand(NewDemand newDemand);
}
