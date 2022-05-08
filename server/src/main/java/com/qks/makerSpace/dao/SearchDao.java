package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.request.SearchRequest;
import com.qks.makerSpace.entity.response.SearchResponse;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchDao {

    @Select("select old_id id, credit_code, name, submit_time, leadership_audit, administrator_audit " +
            "from old " +
            "natural join audit " +
            "where submit_time in (select max(submit_time) from audit group by credit_code) and name like #{content} and submit_time between #{beginTime} and #{endTime}")
    List<SearchResponse> selectOld(SearchRequest searchRequest);

    @Select("select new_id id, credit_code, name, submit_time, leadership_audit, administrator_audit " +
            "from new " +
            "natural join audit " +
            "where submit_time in (select max(submit_time) from audit group by credit_code) and name like #{content} and submit_time between #{beginTime} and #{endTime}")
    List<SearchResponse> selectNew(SearchRequest searchRequest);

    @Select("select administrator_audit from audit where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String selectAdministratorAudit(String creditCode, String submitTime);

    @Select("select leadership_audit from audit where credit_code = #{creditCode} and submit_time = #{submitTime}")
    String selectLeadershipAudit(String creditCode, String submitTime);
}
