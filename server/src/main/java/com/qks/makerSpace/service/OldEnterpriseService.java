package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Map;

public interface OldEnterpriseService {

    Map<String, Object> getOldEnterprise(String token);
    Map<String, Object> getOldEnterpriseById(String token, String oldId);
    Map<String, Object> getOldEnterpriseApplying(String token);
    Map<String, Object> updateOldEnterprise(String token,
                                            String map,
                                            MultipartFile license,
                                            MultipartFile certificate,
                                            MultipartFile[] intellectualFile,
                                            MultipartFile representFile) throws Exception;
    Map<String, Object> getFormByCreditCode(String token) throws ServiceException;
    Map<String, Object> oldEnterpriseContract(String token, MultipartFile voucher) throws ServiceException, IOException;
}
