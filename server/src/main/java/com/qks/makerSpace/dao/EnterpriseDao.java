package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.Enterprise;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseDao {
    Enterprise getOneEnterpriseById(String id);
}
