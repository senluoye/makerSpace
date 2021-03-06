package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.request.SearchRequest;
import com.qks.makerSpace.entity.response.SearchResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
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
}
