package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.Enterprise;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
public interface EnterpriseDao {

    Map<String, Object> getOneEnterpriseById(String id);
    List<Enterprise> getAllEnterprise();
    Timestamp getSubmissionTime(String id);
    Integer addEnterprise(Map<String, Object> map);
    Integer addConnect(String id, String teamName, String enterpriseId);
    Integer updateEnterprise(Map<String, Object> map);
    Integer deleteEnterpriseAll(String enterpriseId);
    Integer addAudit(String auditId, String enterpriseId, Timestamp submissionTime);
    void deleteConnect(String connectId);

}
