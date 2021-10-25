package com.qks.makerSpace.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyResponseUtil {

    /**
     * 这里图个方便，直接定义返回格式，简单易懂
     * @param data
     * @param code
     * @param msg
     * @return
     */
    public static Map<String, Object> getResultMap(Object data, Integer code, String msg){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", code);
        resultMap.put("msg", msg);
        resultMap.put("data", data);
        return resultMap;
    }

}
