package com.github.ailitech.rest.support.interceptor;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.stream.Collectors;

public class HttpIncomeFilter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception{
        String httpBody="";
        if(RequestMethod.POST.name().equalsIgnoreCase(request.getMethod())){
            httpBody= request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        }
        String  basePath=request.getContextPath();
        String uri=request.getRequestURI();
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        String httpBody="";
        if(response instanceof EnhanceHttpServletResponseWrapper){
            httpBody=((EnhanceHttpServletResponseWrapper)response).getResponeData(response.getCharacterEncoding());
        }
    }
}
