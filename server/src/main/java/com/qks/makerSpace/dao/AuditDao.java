package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.Audit;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AuditDao {

    Audit getAuditById(String id);
    Audit changeAuditById(Map<String, Object> map);

}
