package com.qks.makerSpace.handler;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.util.MyResponseUtil;
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
     * 自定义异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    private Map<String, Object> ServiceExceptionHandler(HttpServletRequest req, Exception e) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("最新的请求: " + df.format(new Date()));
        logger.info(req.getRequestURI());
        logger.info(String.valueOf(e));

        return MyResponseUtil.getResultMap(null, -1, e.getMessage());
    }

    /**
     * 空指针异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    private Map<String, Object> nullPointerExceptionHandler(HttpServletRequest req, Exception e) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("最新的请求: " + df.format(new Date()));
        logger.info(req.getRequestURI());
        logger.info(String.valueOf(e));

        return MyResponseUtil.getResultMap(null, -1, "空指针异常");
    }

    /**
     * 数据库连接异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = CommunicationsException.class)
    @ResponseBody
    private Map<String, Object> CommunicationsExceptionHandler(HttpServletRequest req, Exception e) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("最新的请求: " + df.format(new Date()));
        logger.info(req.getRequestURI());
        logger.info(String.valueOf(e));

        return MyResponseUtil.getResultMap(null, -1, "数据库连接异常");
    }

    /**
     * 其他异常
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

        return MyResponseUtil.getResultMap(null, -1, e.getMessage());
    }
}
