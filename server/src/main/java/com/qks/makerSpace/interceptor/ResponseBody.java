package com.qks.makerSpace.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

public class ResponseBody implements ResponseBodyAdvice<Object> {

    /**
     * 是否支持advice功能
     * treu=支持，false=不支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     *
     * 处理response的具体业务方法
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof String) {
            return JsonUtil.object2Json(Result.suc(o));
        }
        return Result.suc(o);
    }
}
