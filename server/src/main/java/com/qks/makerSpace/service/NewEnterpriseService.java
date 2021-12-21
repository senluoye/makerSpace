package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface NewEnterpriseService {

    Map<String, Object> getNewEnterprise();
    Map<String, Object> newRegister(String str, MultipartFile[] file) throws IOException;
    Map<String, Object> newEnterprisePay(Map<String, Object> map);
    Map<String, Object> updateNewEnterprise(String token, String str, MultipartFile[] files) throws IllegalAccessException, IOException, ServiceException, Exception;
    Map<String, Object> newEnterpriseDemand(JSONObject map) throws ServiceException;

    Map<String, Object> getFormByCreditCode(String token) throws ServiceException;
}
