package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Map;

public interface OldEnterpriseService {

    Map<String, Object> getOldEnterprise();
    Map<String, Object> oldRegister(JSONObject map) throws ServiceException;
    Map<String, Object> updateOldEnterprise(String token, String map, MultipartFile[] files) throws Exception;
    Map<String, Object> oldEnterpriseDemand(JSONObject map) throws ServiceException;
    Map<String, Object> getFormByCreditCode(String token) throws ServiceException;
    Map<String, Object> oldEnterpriseContract(String json, MultipartFile voucher) throws ServiceException, IOException;
}
