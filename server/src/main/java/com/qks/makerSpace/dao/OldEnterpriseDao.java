package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.response.FormDetails;
import com.qks.makerSpace.entity.response.OldContractRes;
import com.qks.makerSpace.entity.response.TechnologyApplyingRes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OldEnterpriseDao {

    /**
     * 根据用户id获取用户
     * @param userId
     * @return
     */
    @Select("select * from user where user_id = #{userId}")
    User getUserByUserId(String userId);

    @Select("select user_id from user_company where credit_code = #{creditCode}")
    List<String> selectUserIdByCreditCode(String creditCode);

    @Select("select * from old where old_id = #{oldId}")
    Old getOldByOldId(String oldId);

    @Select("select credit_code from user_company where user_id = #{userId}")
    List<String> getUserCompanyByUserId(String userId);

    @Select("select * from contract where credit_code = #{creditCode}")
    List<Contract> getOldContractList(String creditCode);

    @Insert("insert into contract " +
            "set contract_id = #{contractId}, credit_code = #{creditCode}, " +
            "voucher = #{voucher}, submit_time = #{submitTime}")
    Integer addContract(Contract contract);

    /**
     * 向old表中插入一条记录
     * @param old
     * @return
     */
    @Insert("insert into old(old_id, credit_code, charge, name, represent, " +
            "represent_phone, register_address, represent_email, agent, agent_phone, " +
            "agent_email, license, register_capital, real_address, real_capital, last_income, " +
            "last_tax, employees, origin_number, set_date, nature, certificate, " +
            "involved, main_business, way, business, old_demand_id, old_shareholder_id, " +
            "old_mainperson_id, old_project_id, old_intellectual_id, old_funding_id, cooperation, " +
            "suggestion, note, submit_time, room, outapply_id, representFile) " +
            "VALUES (#{oldId}, #{creditCode}, #{charge}, #{name}, #{represent}, " +
            "#{representPhone}, #{registerAddress}, #{representEmail}, #{agent}, #{agentPhone}, " +
            "#{agentEmail}, #{license}, #{registerCapital}, #{realAddress}, #{realCapital}, #{lastIncome}, " +
            "#{lastTax}, #{employees}, #{originNumber}, #{setDate}, #{nature}, #{certificate}, " +
            "#{involved}, #{mainBusiness}, #{way}, #{business}, #{oldDemandId}, #{oldShareholderId}, " +
            "#{oldMainpersonId}, #{oldProjectId}, #{oldIntellectualId}, #{oldFundingId}, #{cooperation}, " +
            "#{suggestion}, #{note}, #{submitTime}, #{room}, #{outapplyId}, #{representFile})")
    Integer insertOld(Old old);

    /**
     * 根据creditCode拿到旧公司信息
     * @param creditCode
     * @return
     */
    @Select("select * from old " +
            "where submit_time = (" +
            "   select max(submit_time) " +
            "   from old " +
            "   where credit_code = #{creditCode}" +
            ")")
    Old getOld(String creditCode);

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

    @Insert("insert into old_demand(id, lease_area, position, lease, " +
            "floor, electric, water, web, others, old_demand_id, time) " +
            "VALUES (#{id}, #{leaseArea}, #{position}, #{lease}, #{floor}, " +
            "#{electric}, #{water}, #{web}, #{others}, #{oldDemandId}, #{time})")
    Integer addOldDemand(OldDemand oldDemand);

    @Insert("insert into contract(contract_id, credit_code, voucher, time) " +
            "VALUES (#{contractId}, #{creditCode}, #{voucher}, #{submitTime})")
    Integer addOldDemandContract(String contractId, String creditCode, byte[] voucher, String submitTime);

    @Select("select * from old_demand where old_demand_id = #{oldDemandId}")
    List<OldDemand> selectDemandByOldDemandId(String oldDemandId);

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

    /**
     * 根据用户id查看是否已经存入公司代码
     * @param userId
     * @return
     */
    @Select("select * from user_company where user_id = #{userId}")
    List<UserCompany> selectUserCompany(String userId);

    @Select("select old_id from old where credit_code = #{creditCode}")
    List<String> selectOldIdByCreditCode(String creditCode);

    @Select("select audit_id from audit where credit_code = #{creditCode}")
    List<String> selectAuditIdByCreditCode(String creditCode);

    /**
     * old更新creditCode
     * @param oldCreditCode
     * @param newCreditCode
     */
    @Update("update old " +
            "set credit_code = #{newCreditCode} " +
            "where old_id = #{oldId}")
    void updateOldCreditCode(String oldId, String newCreditCode);

    /**
     * 更新audit表的creditCode
     * @param oldCreditCode
     * @param newCreditCode
     */
    @Update("update audit " +
            "set credit_code = #{newCreditCode} " +
            "where audit_id = #{auditId}")
    void updateAuditCreditCode(String auditId, String newCreditCode);

    @Select("select credit_code from user_company where user_id = #{userId}")
    List<String> selectCreditCodeByUserId(String userId);

    @Select("select old_id from old where credit_code = #{creditCode}")
    List<String> getOldIdList(String creditCode);

    @Select("select old.old_id id, old.name, old.suggestion, audit.administrator_audit, audit.leadership_audit, audit.submit_time " +
            "from old, audit " +
            "where old.credit_code = #{creditCode} " +
            "and audit.credit_code = old.credit_code " +
            "and audit.submit_time = old.submit_time " +
            "order by submit_time desc")
    List<TechnologyApplyingRes> selectAuditByCreditCode(String creditCode);

    @Select("select old_demand_id from old where credit_code = #{creditCode}")
    String demandExit(String creditCode);

    // 其他子表的相关操作
    @Update("update user_company " +
            "set credit_code = #{creditCode} " +
            "where user_id = #{userId}")
    Integer updateUserCompany(String userId, String creditCode);

    @Insert("insert into user_company(user_id,credit_code) " +
            "values (#{userId},#{creditCode})")
    Integer insertUserCompany(String userId, String creditCode);

    /**
     * 插入新的入园申请
     * @param audit
     * @return
     */
    @Insert("insert into audit(audit_id, administrator_audit, leadership_audit, `describe`, submit_time, credit_code) " +
            "values (#{auditId}, #{administratorAudit}, #{leadershipAudit}, #{describe}, #{submitTime}, #{creditCode})")
    Integer insertAudit(Audit audit);

    @Select("select * from form where credit_code = #{creditCode}")
    List<FormDetails> getAllFormDetails(String creditCode);

    @Select("select old_id from old where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String getOldIdByCreditCodeAndTime(String creditCode, String submitTime);

    @Select("select name from old where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String getOldNameByCreditCodeAndTime(String creditCode, String submitTime);

    @Select("select suggestion from old where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String getOldSuggestionByCreditCodeAndTime(String creditCode, String submitTime);

    @Select("select note from old where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String getOldNoteByCreditCodeAndTime(String creditCode, String submitTime);
}
