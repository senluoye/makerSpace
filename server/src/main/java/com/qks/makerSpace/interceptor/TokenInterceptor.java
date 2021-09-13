package com.qks.makerSpace.interceptor;

import com.alibaba.fastjson.JSON;
import com.qks.makerSpace.service.UserService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 验证token，是否登录
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);
    /**
     * 忽略拦截的url
     */
    private String urls[] = {
            "/login",
            "/register"
    };

    @Autowired
    private UserService userService;

    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) {

        String token = httpServletRequest.getHeader("token");

        //判断从前端传来的头部信息中AUTH-TOKEN的值是否与我们后台定义的token值一致
        if(JWTUtils.verify(token)){
            return true;
        }else{

            //token错误 返回错误response
            System.out.println("token is error");
            PrintWriter writer = null;

            try {
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.setHeader("Content-Type","application/json");
                writer = httpServletResponse.getWriter();

                //将返回的错误提示压入流中
                writer.write(JSON.toJSONString(MyResponseUtil.getResultMap(null, -1, "token error")));
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != writer) {
                    writer.close();
                }
            }
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) {
    }
}

