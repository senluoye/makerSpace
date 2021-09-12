package com.qks.makerSpace.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyResponseUtil {

    public Map<String, Object> getResultMap(Object data, Integer code, String msg){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", data);
        resultMap.put("code", code);
        resultMap.put("msg", msg);
        return resultMap;
    }

}
