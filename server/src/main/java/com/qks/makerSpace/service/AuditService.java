package com.qks.makerSpace.service;

import java.util.Map;

public interface AuditService {

    Map<String, Object> getAuditById(String id);
    Map<String, Object> changeAuditById(Map<String, Object> map);

}
