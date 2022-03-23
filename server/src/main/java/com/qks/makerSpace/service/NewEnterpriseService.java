package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface NewEnterpriseService {

    Map<String, Object> getFormByCreditCode(String token) throws ServiceException;
    Map<String, Object> newEnterpriseContract(String json, MultipartFile voucher) throws ServiceException, IOException;
    Map<String, Object> getNewEnterpriseApplying(String token);
    Map<String, Object> updateNewEnterprise(String token,
                                    String str,
                                    MultipartFile picture,
                                    MultipartFile representCard,
                                    MultipartFile certificate,
                                    MultipartFile[] intellectualFile) throws Exception;

    Map<String, Object> getNewEnterprise(String token);
}
