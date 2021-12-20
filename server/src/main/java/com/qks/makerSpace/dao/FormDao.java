package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.UserCompany;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FormDao {
    //从数据空中那数据，没有考虑图片，所以可以直接全拿，在工具类中会忽略没有用的字段值
    @Select("select * from form where credit_code = #{creditCode}")
    Map<String , Object> getAllInformation(String creditCode);

    @Select("select * from user_company where user_id = #{userId}")
    List<UserCompany> getCompanyByUserId(String userId);

}
