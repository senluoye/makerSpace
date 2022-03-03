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

    /**
     * 根据用户id获取用户
     * @param userId
     * @return
     */
    @Select("select * from user where user_id = #{userId}")
    User getUserByUserId(String userId);

    /**
     * 向old表中插入一条记录
     * @param old
     * @return
     */
    @Insert("insert into old(old_id, credit_code, charge, " +
            "name, represent, represent_phone, register_address, " +
            "represent_email, agent, agent_phone, agent_email, license, " +
            "register_capital, real_address, real_capital, last_income, " +
            "last_tax, employees, origin_number, set_date, nature, " +
            "certificate, involved, main_business, way, business, " +
            "old_demand_id, old_shareholder_id, old_mainperson_id, " +
            "old_project_id, old_intellectual_id, old_funding_id, " +
            "cooperation, suggestion, note, state, submit_time, room, outapply_id) " +
            "VALUES (#{oldId}, #{creditCode}, #{charge}, #{name}, #{represent}, " +
            "#{representPhone}, #{registerAddress}, #{representEmail}, #{agent}, #{agentPhone}, #{agentPhone}, " +
            "#{agentEmail}, #{license}, #{registerCapital}, #{realAddress}, #{realCapital}, #{lastIncome}, " +
            "#{lastTax}, #{employees}, #{originNumber}, #{setDate}, #{nature}, #{certificate}, " +
            "#{involved}, #{mainBusiness}, #{way}, #{business}, #{oldDemandId}, #{oldShareholderId}, " +
            "#{oldMainpersonId}, #{oldProjectId}, #{oldIntellectualId}, #{oldFundingId}, #{cooperation}, #{state}, " +
            "#{submitTime}, #{room}, #{oldInapplyId}, #{oldOutapplyId})")
    Integer insertOld(Old old);

    /**
     * 根据creditCode拿到旧公司信息
     * @param creditCode
     * @return
     */
    @Select("select * from old where credit_code = #{creditCode}")
    List<Old> getAllOld(String creditCode);

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

}
