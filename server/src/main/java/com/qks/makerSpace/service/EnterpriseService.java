package com.qks.makerSpace.service;

import java.util.Map;

public interface EnterpriseService {

    Map<String, Object> getOneEnterprise(String id);
    Map<String, Object> getAllEnterprise();
    Map<String, Object> addEnterprise(Map<String, Object> map);
    Map<String, Object> updateEnterprise(Map<String, Object> map);
    Map<String, Object> deleteEnterprise(String id);

}
