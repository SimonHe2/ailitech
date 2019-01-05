package com.github.ailitech.rest.support.incomefilter;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpIncomeFilter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception{

        String  basePath=request.getContextPath();
        String uri=request.getRequestURI();
        return true;
    }
}
