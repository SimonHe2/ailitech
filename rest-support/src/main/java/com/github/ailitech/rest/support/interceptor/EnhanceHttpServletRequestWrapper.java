package com.github.ailitech.rest.support.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class EnhanceHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final String httpBody;
    public EnhanceHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        httpBody=getBodyContent(request);
    }

    @Override
    public BufferedReader getReader() throws IOException{
        ServletInputStream inputStream=getInputStream();
        if(inputStream==null){
            return null;
        }
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException{
        if(StringUtils.isBlank(httpBody)){
            return null;
        }
        final ByteArrayInputStream inputStream=new ByteArrayInputStream(httpBody.getBytes(Charset.forName("UTF-8")));
        ServletInputStream result= new ServletInputStream(){

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
        return result;
    }

    private String getBodyContent(HttpServletRequest request){
        try{
            if(RequestMethod.POST.name().equalsIgnoreCase(request.getMethod())){
                return request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }catch (Exception ex){

        }
        return null;
    }


}
