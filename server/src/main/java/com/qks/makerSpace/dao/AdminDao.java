package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.request.AdminSpaceApplyingReq;
import com.qks.makerSpace.entity.request.AdminTechnologyApplyingReq;
import com.qks.makerSpace.entity.request.BriefFormReq;
import com.qks.makerSpace.entity.request.FormReq;
import com.qks.makerSpace.entity.response.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminDao {

    @Select("select * from user_account_applying where name = #{name}")
    UserAccountApplying getUserFormApplyingByName(String name);

    /**
     * 根据时间获取所有用户账号申请
     * @return
     */
    @Select("select user_account_id, name, email, `describe`, submit_time " +
            "from user_account_applying where administrator_audit = 0 order by submit_time")
    List<UserAccountApplyingRes> getUserAccountApplying();

    /**
     * 根据名称查找用户
     * @param name
     * @return
     */
    @Select("select * from user where name = #{name}")
    List<User> getUserByName(String name);

    /**
     * 增加新用户
     * @param user
     * @return
     */
    @Insert("insert into user(user_id, name, password, user_describe, email) " +
            "values (#{userId}, #{name}, #{password}, #{userDescribe}, #{email})")
    Integer addNewUser(User user);

    @Update("update user " +
            "set name = #{name}, password = #{password}, user_describe = #{userDescribe}," +
            "email = #{email} " +
            "where user_id = #{userId}")
    Integer UpdateUser(User user);

    @Delete("delete from user_account_applying where user_account_id = #{userId}")
    Integer deleteUserAccountApplying(String userId);

    /**
     *  获取最新所有未审核科技园入园申请
     * @return
     */
    @Select("select credit_code, administrator_audit administratorAudit, leadership_audit leadershipAudit, `describe`, max(submit_time) submitTime " +
            "from (select * from audit where `describe` = '科技园' and administrator_audit = '未审核') temp " +
            "group by credit_code")
    List<AdminTechnologyApplyingReq> getAllTechnologyApplying();

    @Select("select credit_code, administrator_audit administratorAudit, leadership_audit leadershipAudit, `describe`, max(submit_time) submitTime " +
            "from (select * from audit where `describe` = '科技园') temp " +
            "group by credit_code")
    List<AdminTechnologyApplyingReq> getAllApplying();

    @Select("select credit_code, administrator_audit administratorAudit, leadership_audit leadershipAudit, `describe`, max(submit_time) submitTime " +
            "from (select * from audit where `describe` = '科技园' and administrator_audit = '通过') temp " +
            "group by credit_code")
    List<AdminTechnologyApplyingReq> getAllApplied();


    @Select("select credit_code inApplyId, administrator_audit administratorAudit, `describe`, max(submit_time) submitTime " +
            "from (select * from audit where `describe` = '众创空间' and administrator_audit = '未审核') temp " +
            "group by credit_code ")
    List<AdminSpaceApplyingReq> getAllSpaceApplying();

    @Select("select old_id id, name, max(submit_time) submit_time, credit_code from old group by credit_code")
    List<AllTechnologyApplyingRes> getTechnologyApplying();

    @Select("select credit_code, administrator_audit, max(submit_time) from audit group by credit_code")
    @MapKey("credit_code")
    Map<String, Map<String, Object>> getAudit();

    @Select("select name from old where credit_code = #{creditCode}")
    List<String> getOldNameByCreditCode(String id);

    @Select("select name from new where credit_code = #{creditCode}")
    List<String> getNewNameByCreditCode(String creditCode);

    @Select("select create_name name from space where in_apply_id = #{in_apply_id}")
    List<String> getSpaceNameByCreditCode(String inApplyId);

    @Select("select old.old_id as id, old.credit_code as creditCode, " +
            "old.name as name, old.represent as represent, old.represent_phone as representPhone, " +
            "old.represent_email as representEmail, old_demand.floor as floor, old_demand.position as position, " +
            "old.alive as alive, audit.administrator_audit as administratorAudit, audit.leadership_audit as leadershipAudit " +
            "from old, old_demand, audit " +
            "where old.old_demand_id = old_demand.old_demand_id " +
            "and audit.credit_code = old.credit_code " +
            "group by old.credit_code")
    List<AllTechnology> getAllOldDetails();

    @Select("select new.new_id as id, new.credit_code as creditCode, " +
            "new.name as name, new.represent as represent, new.represent_phone as representPhone, " +
            "new.represent_email as representEmail, new_demand.floor as floor, new_demand.position as position, " +
            "new.alive as alive, audit.administrator_audit as administratorAudit, audit.leadership_audit as leadershipAudit " +
            "from new, new_demand, audit " +
            "where new.new_demand_id = new_demand.new_demand_id " +
            "and audit.credit_code = new.credit_code " +
            "group by new.credit_code")
    List<AllTechnology> getAllNewDetails();

    @Select("select in_apply_id, create_name, apply_time, team_number, " +
            "space.`describe`, help, space_id " +
            "from space, audit " +
            "where audit.credit_code = space.in_apply_id ")
    List<Space> getAllSpaceDetails();

    @Select("select * from space_person where in_apply_id = #{inApplyId}")
    List<SpacePerson> getSpacePeopleById(String inApplyId);

    @Select("select * from old where old_id = #{id}")
    Old getOld(String id);

    @Select("select old_id from old where credit_code = #{creditCode}")
    String getOldId(String creditCode);

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

    @Select("select * from new where new_id = #{id}")
    News getNew(String id);

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

    @Select("select * from space where in_apply_id = #{id}")
    Space getSpaceById(String id);

    @Select("select * from space_person where in_apply_id = #{inApplyId}")
    List<SpacePerson> getPersonListByInApplyId(String inApplyId);

    @Delete("delete space, space_person " +
            "from space, space_person " +
            "where space.in_apply_id = space_person.in_apply_id " +
            "and space.in_apply_id = #{inApplyId}")
    Integer deleteSpaceByCreditCode(String inApplyId);

    @Delete("delete old, old_demand, old_funding, old_intellectual, " +
            "old_mainperson, old_project, old_shareholder " +
            "from old, old_demand, old_funding, old_intellectual, " +
            "old_mainperson, old_project, old_shareholder " +
            "where old.old_demand_id = old_demand.old_demand_id " +
            "and old.old_shareholder_id = old_shareholder.old_shareholder_id " +
            "and old.old_mainperson_id = old_mainperson.old_mainperson_id " +
            "and old.old_project_id = old_project.old_project_id " +
            "and old.old_intellectual_id = old_intellectual.old_intellectual_id " +
            "and old.old_funding_id = old_funding.funding_id " +
            "and old.credit_code = #{creditCode}")
    Integer deleteOldByCreditCode(String creditCode);

    @Update("update audit set administrator_audit = #{agree} where audit_id = #{id}")
    Integer agreeById(String id, String agree);

    @Select("select credit_code from old where old_id = #{oldId}")
    List<String> getOldCreditCodeById(String oldId);

    @Select("select credit_code from new where new_id = #{newId}")
    List<String> getNewCreditCodeById(String newId);

    @Select("select * " +
            "from audit " +
            "where credit_code = #{creditCode} " +
            "and submit_time = (" +
            "   select max(submit_time) " +
            "   from audit " +
            "   where credit_code = #{creditCode} " +
            ")")
    Audit getLastAuditByCreditCode(String creditCode);

    @Select("select distinct year from form")
    List<String> getTimeList();

    @Select("select team_name, credit_code, get_time, admin_audit, leader_audit " +
            "from form " +
            "where year = #{year} and quarter = #{quarter} "
//            + "group by credit_code"
            )
    List<Form> getFormListByTime(String year, String quarter);

    @Update("update space set office_opinion = #{officeOpinion}, leader_opinion = #{leaderOpinion} " +
            "where in_apply_id = #{inApplyId}")
    Integer updateSpaceBySuggestion(AdminSpaceSuggestion adminSpaceSuggestion);

    @Update("update audit set administrator_audit = #{disagree} where credit_code = #{inApplyId}")
    Integer disagreeById(String inApplyId, String disagree);

    @Select("select * from audit " +
            "where credit_code = #{id} " +
            "and submit_time = (" +
            "   select max(submit_time) from audit where credit_code = #{id}" +
            ")")
    Audit getAuditByCreditCode(String id);

    @Select("select * from old where old_id = #{oldId}")
    List<Old> getOldById(String oldId);

    @Select("select * from new where new_id = #{newId}")
    List<News> getNewById(String newId);

    @Select("select credit_code from new where credit_code = #{creditCode}")
    List<String> selectCreditCodeFromNewByCreditCode(String creditCode);

    @Select("select credit_code from old where credit_code = #{creditCode}")
    List<String> selectCreditCodeFromOldByCreditCode(String creditCode);

    @Update("update new set suggestion = #{suggestion}, note = #{note} where credit_code = #{creditCode}")
    Integer updateNewSuggestion(AdminSuggestion adminSuggestion);

    @Update("update old set suggestion = #{suggestion}, note = #{note} " +
            "where credit_code = #{creditCode}")
    Integer updateOldSuggestion(AdminSuggestion adminSuggestion);


//----季度报表操作从此处-----
    @Select("select form_id as id, year, quarter, team_name, credit_code, get_time, admin_audit, leader_audit, alive " +
            "from form where get_time in (select max(get_time) from form " +
            "group by credit_code) and admin_audit <> '2'")
    List<BriefFormReq> getDoubleAudit();

    @Select("select form_id as id, year, quarter, team_name, credit_code, get_time get_time, admin_audit, leader_audit, alive " +
            "from form where get_time in (select max(get_time) from form " +
            "group by credit_code) and admin_audit = '2' and leader_audit <> '2' ")
    List<BriefFormReq> getLeaderAudit();

    @Select("select form_id as id, year, quarter, team_name, credit_code, get_time get_time, admin_audit, leader_audit, alive " +
            "from form where get_time in (select max(get_time) from form" +
            "group by credit_code) and admin_audit = '2' and leader_audit = '2' ")
    List<BriefFormReq> getAudited();

    @Select("select * from form where form_id = id")
    FormReq getDetailForm(String id);

    @Select("select year, quarter, team_name, credit_code, get_time, admin_audit, leader_audit from form where credit_code = #{creditCode}")
    List<BriefFormReq> getCompanyForm(String creditCode);

    @Delete("delete from form where credit_code = #{creditCode} and get_time = #{getTime}")
    Integer deleteForm(String creditCode, String getTime);

    @Update("update form set admin_audit = '2' where form_id = #{formId}")
    Integer agreeForm(String formId);

    @Update("update form set admin_audit = '1' where form_id = #{formId}")
    Integer disagreeForm(String formId);

    @Select("select old_id from old where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String selectOldIdByTimeAndCreditCode(String creditCode, String submitTime);

    @Select("select new_id from new where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String selectNewIdByTimeAndCreditCode(String creditCode, String submitTime);
}
