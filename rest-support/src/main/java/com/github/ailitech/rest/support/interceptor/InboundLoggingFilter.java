package com.github.ailitech.rest.support.interceptor;


import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InboundLoggingFilter implements Filter {
    private Logger logger= LoggerFactory.getLogger(InboundLoggingFilter.class.getName());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CachingContentRequestWrapper requestWrapper=null;
        CachingContentResponseWrapper responseWrapper=null;
        if(servletRequest instanceof HttpServletRequest){
            requestWrapper=new CachingContentRequestWrapper((HttpServletRequest)servletRequest);
        }
        if(servletResponse instanceof HttpServletResponse){
            responseWrapper=new CachingContentResponseWrapper((HttpServletResponse) servletResponse);
        }
        long starttime=System.currentTimeMillis();
        filterChain.doFilter(requestWrapper,responseWrapper);
        long endtime=System.currentTimeMillis();
        long costtime=endtime-starttime;
        logger.info("request:{},queryString:{},payload:{}",
                requestWrapper.getRequestURL().toString(),
                requestWrapper.getQueryString()!=null?requestWrapper.getQueryString():"null",
                requestWrapper.getPayload()!=null?requestWrapper.getPayload():"null");
        logger.info("response payload:{}",responseWrapper.getResponeData());
        logger.info("cost:{}",costtime);

        byte[] result=responseWrapper.getResponeData().getBytes();
        servletResponse.setContentType(responseWrapper.getContentType());
        servletResponse.setContentLength(result.length);
        servletResponse.getOutputStream().write(result);
    }

    @Override
    public void destroy() {

    }
}
