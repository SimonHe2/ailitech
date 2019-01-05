package com.github.ailitech.rest.support.interceptor;


import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReplaceHttpServletRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest request=servletRequest;
        ServletResponse response=servletResponse;
        if(servletRequest instanceof HttpServletRequest){
            request=new EnhanceHttpServletRequestWrapper((HttpServletRequest)servletRequest);
        }
        if(servletResponse instanceof HttpServletResponse){
            response=new EnhanceHttpServletResponseWrapper((HttpServletResponse) servletResponse);
        }

        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
