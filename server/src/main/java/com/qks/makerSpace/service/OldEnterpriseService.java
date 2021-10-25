package com.qks.makerSpace.service;

import java.util.Map;

public interface OldEnterpriseService {

    Map<String, Object> getOldEnterprise();
    Map<String, Object> oldRegister(Map<String, Object> map);
    Map<String, Object> applyForMakerSpace(Map<String, Object> map);
    Map<String, Object> oldEnterprisePay(Map<String, Object> map);
    Map<String, Object> updateOldEnterprise(Map<String, Object> map);
    Map<String, Object> quitMakerSpace(Map<String, Object> map);

}
