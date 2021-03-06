package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.Temp.EmploymentData;
import com.qks.makerSpace.entity.Temp.FormAwardsData;
import com.qks.makerSpace.entity.Temp.HighEnterpriseData;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.request.*;
import com.qks.makerSpace.entity.response.TimeFormRes;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface LeaderDao {

    @Select("select credit_code, administrator_audit administratorAudit, leadership_audit leadershipAudit, `describe`, max(submit_time) submitTime " +
            "from (select * from audit where `describe` = '科技园' and leadership_audit = '未审核' and administrator_audit = '通过' ) temp " +
            "group by credit_code")
    List<LeaderTechnologyApplyingReq> getAllTechnologyApplying();

    @Select("select credit_code, administrator_audit administratorAudit, leadership_audit leadershipAudit, `describe`, max(submit_time) submitTime " +
            "from (select * from audit where `describe` = '科技园' and leadership_audit = '通过') temp " +
            "group by credit_code")
    List<LeaderTechnologyApplyingReq> getAllApplied();

    @Select("select credit_code, administrator_audit administratorAudit, leadership_audit leadershipAudit, `describe`, max(submit_time) submitTime " +
            "from (select * from audit where `describe` = '科技园' and administrator_audit = '通过' ) temp " +
            "group by credit_code")
    List<LeaderTechnologyApplyingReq> getAllApplying();

    @Select("select credit_code inApplyId, leadership_audit leadershipAudit, `describe`, max(submit_time) submitTime " +
            "from (select * from audit where `describe` = '众创空间' and leadership_audit = '未审核') temp " +
            "group by credit_code ")
    List<LeaderSpaceApplyingReq> getAllSpaceApplying();

    @Select("select name from old where credit_code = #{creditCode}")
    List<String> getOldNameByCreditCode(String creditCode);

    @Select("select name from new where credit_code = #{creditCode}")
    List<String> getNewNameByCreditCode(String creditCode);

    @Select("select create_name name from space where in_apply_id = #{in_apply_id}")
    List<String> getSpaceNameByCreditCode(String inApplyId);

    @Update("update form set leader_audit = '通过' where form_id = #{formId}")
    Integer agreeForm(String formId);

    @Update("update form set leader_audit = '未通过' where form_id = #{formId}")
    Integer disagreeForm(String formId);

    @Select("select year, quarter, team_name, credit_code, get_time, admin_audit, leader_audit " +
            "from form where get_time in (select max(get_time) from form" +
            "group by credit_code) and admin_audit = '通过' and leader_audit = '未审核' ")
    List<BriefFormReq> getLeaderAudit();

    @Select("select * from form where form_id = #{id}")
    Form getDetailForm(String id);

    @Select("select year, quarter, team_name, credit_code, get_time, admin_audit, leader_audit from form where credit_code = #{creditCode}")
    List<BriefFormReq> getCompanyForm(String creditCode);

    @Select("select old_id from old where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String selectOldIdByTimeAndCreditCode(String creditCode, String submitTime);

    @Select("select new_id from new where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String selectNewIdByTimeAndCreditCode(String creditCode, String submitTime);

    @Select("select * from old where old_id = #{id}")
    Old getOld(String id);

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

    @Select("select distinct year from form")
    List<String> getTimeList();

    @Select("select form_id, team_name, credit_code, get_time, admin_audit, leader_audit, concat(year, quarter) time " +
            "from form " +
            "where year = #{year} and quarter = #{quarter} "
//            + "group by credit_code"
    )
    List<TimeFormRes> getFormListByTime(String year, String quarter);

    @Select("select * from form_high_enterprise where high_enterprise_id = #{highEnterpriseId}")
    HighEnterpriseData getHighEnterpriseById(String highEnterpriseId);

    @Select("select * from form_employment where form_employment_id = #{employmentId}")
    List<EmploymentData> getEmploymentById(String employmentId);

    @Select("select * from form_awards where form_awards_id = #{awardsId}")
    List<FormAwardsData> getFormAwardsById(String awardsId);

    @Select("select * from audit where credit_code = #{creditCode} and submit_time = #{submitTime}")
    Audit getSameAuditByCreditCode(String creditCode, String submitTime);

    @Update("update audit set leadership_audit = #{agree} where audit_id = #{id}")
    Integer agreeById(String id, String agree);

    @Select("select * from old where old_id = #{oldId}")
    List<Old> getOldById(String oldId);

    @Select("select * from new where new_id = #{newId}")
    List<News> getNewById(String newId);

    @Select("select * from user where user_id = #{userId}")
    List<User> getUserById(String userId);

    @Select("select * from contract")
    List<Contract> getAllContract();

    @Select("select name from user where user_id = (select user_id from user_company where credit_code = #{creditCode})")
    String getNameByCreditCode(String creditCode);
}
