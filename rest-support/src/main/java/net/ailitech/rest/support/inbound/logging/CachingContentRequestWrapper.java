package net.ailitech.rest.support.inbound.logging;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public class CachingContentRequestWrapper extends HttpServletRequestWrapper {

    private final String payload;
    private final String charset;
    public CachingContentRequestWrapper(HttpServletRequest request) throws  IOException {
        super(request);
        payload = getPayload(request);
        charset= request.getCharacterEncoding();
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
        if(StringUtils.isBlank(payload)){
            return null;
        }
        final ByteArrayInputStream inputStream=new ByteArrayInputStream(payload.getBytes(charset));
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

    public String getPayload(){
        return payload;
    }
    private String getPayload(HttpServletRequest request) throws IOException{

        if(RequestMethod.POST.name().equalsIgnoreCase(request.getMethod())){
            return request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        }

        return null;
    }


}
