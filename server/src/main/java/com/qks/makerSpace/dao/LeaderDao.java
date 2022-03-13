package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.request.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderDao {

    @Select("select credit_code, leadership_audit leadershipAudit, `describe`, max(submit_time) submitTime " +
            "from (select * from audit where `describe` = '科技园' and leadership_audit = '未审核') temp " +
            "group by credit_code")
    List<LeaderTechnologyApplyingReq> getAllTechnologyApplying();

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
}
