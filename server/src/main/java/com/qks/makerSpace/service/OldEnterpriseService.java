package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;


import java.util.Map;

public interface OldEnterpriseService {

    Map<String, Object> getOldEnterprise();
    Map<String, Object> oldRegister(Map<String, Object> map);
    Map<String, Object> oldEnterprisePay(Map<String, Object> map);
    Map<String, Object> updateOldEnterprise(String token,
                                            String map,
                                            MultipartFile[] files) throws Exception;
    Map<String, Object> oldEnterpriseDemand(JSONObject map) throws ServiceException;

}
