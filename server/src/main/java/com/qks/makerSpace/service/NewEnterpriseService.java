package com.qks.makerSpace.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface NewEnterpriseService {

    Map<String, Object> getNewEnterprise();
    Map<String, Object> newRegister(Map<String, Object> map, MultipartFile file);
    Map<String, Object> NewApplyForMakerSpace(Map<String, Object> map);
    Map<String, Object> newEnterprisePay(Map<String, Object> map);
    Map<String, Object> updateNewEnterprise(Map<String, Object> map);
    Map<String, Object> quitNewMakerSpace(Map<String, Object> map);
}
