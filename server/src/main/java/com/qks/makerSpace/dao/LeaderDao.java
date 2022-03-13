package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.request.LeaderSpaceApplyingReq;
import com.qks.makerSpace.entity.request.LeaderTechnologyApplyingReq;
import com.qks.makerSpace.entity.request.AdminSpaceApplyingReq;
import org.apache.ibatis.annotations.Select;
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

}
