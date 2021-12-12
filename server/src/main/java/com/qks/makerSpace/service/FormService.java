package com.qks.makerSpace.service;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface FormService {

    Map<String, Object> getDownLoadForm();
    void downLoadWord(HttpServletResponse response, Map<String, Object> map, int i) throws Exception;
}
