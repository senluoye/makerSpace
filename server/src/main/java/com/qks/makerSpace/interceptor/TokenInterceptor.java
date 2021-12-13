package com.qks.makerSpace.interceptor;

import com.qks.makerSpace.util.JWTUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 验证token，是否登录
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             @NotNull HttpServletResponse httpServletResponse,
                             Object o) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("最新的请求: " + df.format(new Date()));

        return JWTUtils.verify(httpServletRequest.getHeader("token"));

    }

    @Override
    public void postHandle(@NotNull HttpServletRequest httpServletRequest,
                           @NotNull HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) {
        System.out.println("请求方法: " + o.toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) {
    }
}

