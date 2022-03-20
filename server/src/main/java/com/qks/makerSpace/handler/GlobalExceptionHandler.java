package com.qks.makerSpace.handler;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

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
        logger.info("最新的请求: " + df.format(new Date()));
        logger.info(req.getRequestURI());
        logger.info(String.valueOf(e));



        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("data", null);
        modelMap.put("code", -1);
        modelMap.put("msg", e.getMessage());

        if (e instanceof CommunicationsException)
            modelMap.put("msg", "数据库连接超时");
        else
            modelMap.put("msg", e.getMessage());

        return modelMap;
    }
}
