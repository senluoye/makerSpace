package com.qks.makerSpace.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private Map<String, Object> exceptionHandler(HttpServletRequest req, Exception e) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("最新的请求: " + df.format(new Date()));
        e.printStackTrace();

        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("data", null);
        modelMap.put("code", -1);
//        modelMap.put("msg", e.getMessage());
        modelMap.put("msg", "数据解析错误或填写数据不完整");
        return modelMap;
    }
}
