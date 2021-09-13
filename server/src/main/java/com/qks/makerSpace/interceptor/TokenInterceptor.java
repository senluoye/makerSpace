package com.qks.makerSpace.interceptor;

import com.qks.makerSpace.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

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
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {

        String url = httpServletRequest.getRequestURI();
        String token = httpServletRequest.getHeader("token");
        String method = httpServletRequest.getMethod();

        if (!method.equals("OPTIONS")){
            logger.info(token);
            logger.info(url);
            logger.info(method);

            // 遍历需要忽略拦截的路径
            for (String item : this.urls){
                if (item.equals(url)){
                    return true;
                }
            }

            // 查询验证token
            User userByToken = userService.getUserByToken(token);

            if (userByToken == null){
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json; charset=utf-8");
                PrintWriter out = null ;

                try{
                    Result res = new Result(10001,"登录失效重新登录");
                    String json = JSON.toJSONString(res);
                    httpServletResponse.setContentType("application/json");
                    out = httpServletResponse.getWriter();
                    // 返回json信息给前端
                    out.append(json);
                    out.flush();
                    return false;
                } catch (Exception e){
                    e.printStackTrace();
                    httpServletResponse.sendError(500);
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // System.out.println("处理请求完成后视图渲染之前的处理操作");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // System.out.println("视图渲染之后的操作");
    }
}

