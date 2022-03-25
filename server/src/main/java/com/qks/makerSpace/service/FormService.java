package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FormService {

    Map<String, Object> setTechnologyForm(
            String token,
            JSONObject map,
            MultipartFile mediumFile,
            MultipartFile highEnterpriseFile,
            MultipartFile headerFile,
            MultipartFile[] contractFile,
            MultipartFile[] awardsFile) throws ServiceException, IOException;
    Map<String, Object> getTechnologyForm(String token) throws ServiceException, IOException;
    Map<String, Object> getTechnologyFormById(String token, String formId);
    Map<String, Object> getTechnologyBasic(String token) throws ServiceException;

    Map<String, Object> getDownLoadForm(String formId) throws IllegalAccessException;
    void downLoadWord(HttpServletResponse response, Map<String, Object> map) throws Exception;
    Map<String, Object> adminGetTechnologyForm(String token) throws ServiceException;
    Map<String, Object> userGetTechnologyForm(String token) throws ServiceException;
    void spaceDownLoad(HttpServletResponse response, String inApplyId) throws ServiceException, IllegalAccessException;
}
