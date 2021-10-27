package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.Old;
import com.qks.makerSpace.entity.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OldEnterpriseDao {

    @Select("insert into old(old_id, credit_code, organization_code, name, password, " +
            "represent, represent_phone, register_address, represent_email, agent, agent_phone, agent_email )" +
            "VALUES (#{oldId}, #{creditCode}, #{organizationCode}, #{name}, #{password}, #{represent}, " +
            "#{representPhone}, #{registerAddress}, #{representEmail}, #{agent}, #{agentPhone}, #{agentEmail})")
    Integer oldRegister(Old old);



}
