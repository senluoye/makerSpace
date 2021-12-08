package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface NewEnterpriseService {

    Map<String, Object> getNewEnterprise();
    Map<String, Object> newRegister(Map<String, Object> map, MultipartFile[] file) throws IOException;
    Map<String, Object> NewMakerSpace(String token, Map<String, Object> map);
    Map<String, Object> newEnterprisePay(Map<String, Object> map);
    Map<String, Object> updateNewEnterprise(String token, Map<String, Object> map, MultipartFile[] files) throws IllegalAccessException, IOException;
    Map<String, Object> quitNewMakerSpace(Map<String, Object> map);
}
