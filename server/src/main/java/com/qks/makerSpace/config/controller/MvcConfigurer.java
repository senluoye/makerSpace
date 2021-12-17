package com.qks.makerSpace.config.controller;

import com.qks.makerSpace.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将加一个拦截器，检查会话，设置的请求都经过此拦截器
        registry.addInterceptor(new TokenInterceptor())
                .excludePathPatterns("/api/register/**","/api/new/**","/api/old/**","/api/login/**","/api/**")
                .addPathPatterns("/api/**");
    }

    //跨域访问 配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //限定访问路径为/api
        registry.addMapping("/api/**")
                .allowedMethods("POST", "GET", "PUT" ,"DELETE");
    }
}
