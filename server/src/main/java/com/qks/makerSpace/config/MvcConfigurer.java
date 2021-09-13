package com.qks.makerSpace.config;

import com.qks.makerSpace.interceptor.TokenInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfigurer implements WebMvcConfigurer {

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将加一个拦截器，检查会话，所有/admin开头的请求都经过此拦截器
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/api/enterprise/**")
                .addPathPatterns("/api/employee/**")
                .addPathPatterns("/api/property/**")
                .addPathPatterns("/api/activity");
    }

    //跨域访问 配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //限定访问路径为/api
        registry.addMapping("/api/**")
                .allowedMethods("POST", "GET", "PUT" ,"DELETE");
    }
}
