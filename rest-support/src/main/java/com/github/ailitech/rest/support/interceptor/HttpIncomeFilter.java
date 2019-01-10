package com.github.ailitech.rest.support.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

public class HttpIncomeFilter extends HandlerInterceptorAdapter {
    private static Logger logger= LoggerFactory.getLogger(HttpIncomeFilter.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception{
        String httpBody="";
        if(RequestMethod.POST.name().equalsIgnoreCase(request.getMethod())){
            httpBody= request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        }
        String  basePath=request.getContextPath();
        String uri=request.getRequestURI();
        String uriParam=request.getQueryString();
        logger.info("request basePath {},uri {},uriparameter {}",basePath,uri,uriParam);
        if(StringUtils.isNotBlank(httpBody)){
            logger.info("httpBody: {}",httpBody);
        }

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
        logger.info("response {}",httpBody);
        logger.debug("test debug");
    }
}
