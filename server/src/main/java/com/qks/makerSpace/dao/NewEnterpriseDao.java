package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.News;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;


@Repository
public interface NewEnterpriseDao {

    @Insert("insert into " +
            "new(credit_code,organization_code,password,name,picture,represent,represent_card,represent_phone,represent_email,agent,agent_phone,agent_email)" +
            "VALUES (" +
            "#{credit_code},#{organization_code},#{password}," +
            "#{name},#{picture},#{represent},#{represent_card}," +
            "#{represent_phone},#{represent_email},#{agent}," +
            "#{agent_phone},#{agent_email})")
    int newRegister(News news);


    int quitNewEnterprise();

}
