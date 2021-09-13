package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.Enterprise;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnterpriseDao {
    Enterprise getOneEnterpriseById(String id);
    List<Enterprise> getAllEnterprise();
    Integer addEnterprise();
}
