package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.request.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

    @Update("update form set leader_audit = '2' where form_id = #{formId}")
    Integer agreeForm(String formId);

    @Update("update form set leader_audit = '1' where form_id = #{formId}")
    Integer disagreeForm(String formId);

    @Select("select time, team_name, credit_code, get_time get_time, admin_audit, leader_audit " +
            "from form where get_time in (select max(get_time) from form" +
            "group by credit_code) and admin_audit = '2' and leader_audit = '0' ")
    List<BriefFormReq> getLeaderAudit();

    @Select("select * from form where credit_code = #{creditCode} and get_time = #{getTime}")
    FormReq getDetailForm(String creditCode, String getTime);

    @Select("select time, team_name, credit_code, get_time, admin_audit, leader_audit from form where credit_code = #{creditCode}")
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
}
