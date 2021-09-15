package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.Enterprise;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EnterpriseDao {

    Enterprise getOneEnterpriseById(String id);
    List<Enterprise> getAllEnterprise();
    void addEnterprise(Map<String, Object> map);
    Integer updateEnterprise(Map<String, Object> map);
    Integer deleteEnterprise(String id);

}
