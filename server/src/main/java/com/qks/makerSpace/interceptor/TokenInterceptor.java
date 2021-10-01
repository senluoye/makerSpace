package com.qks.makerSpace.interceptor;

import com.qks.makerSpace.util.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证token，是否登录
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

//    private final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

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

        //判断从前端传来的头部信息中AUTH-TOKEN的值是否与我们后台定义的token值一致
        //token错误 返回错误response
        //            System.out.println("token is error");
        //            PrintWriter writer = null;
        //
        //            try {
        //                httpServletResponse.setCharacterEncoding("utf-8");
        //                httpServletResponse.setHeader("Content-Type","application/json");
        //                writer = httpServletResponse.getWriter();
        //
        //                //将返回的错误提示压入流中
        //                writer.write(JSON.toJSONString(MyResponseUtil.getResultMap(null, -1, "token error")));
        //                writer.flush();
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //            } finally {
        //                if (null != writer) {
        //                    writer.close();
        //                }
        //            }

        /*
         *  其实登陆验证几乎算是没有，因为只有单用户，并且没有设置如过期时间等
         *  pom加入了redis的依赖，实际也是用不到的
         */
//        return JWTUtils.verify(httpServletRequest.getHeader("token"));

        return true;
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

