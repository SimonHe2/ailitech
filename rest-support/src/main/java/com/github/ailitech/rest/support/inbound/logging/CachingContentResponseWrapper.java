package com.github.ailitech.rest.support.inbound.logging;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

public class CachingContentResponseWrapper extends HttpServletResponseWrapper {
    private final ServletOutputStream servletOutputStream;
    private final ByteArrayOutputStream contentBuffer;
    private final PrintWriter printWriter;
    private final String characterEncoding;
    public CachingContentResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        characterEncoding=response.getCharacterEncoding();
        contentBuffer =new ByteArrayOutputStream();
        servletOutputStream=new ServletOutputStream(){
            @Override
            public void write(int b) throws IOException {
                contentBuffer.write(b);
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener listener) {

            }
        };
        printWriter=new PrintWriter(new OutputStreamWriter(contentBuffer,response.getCharacterEncoding()));
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException{
        return servletOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException{
        return printWriter;
    }
    @Override
    public void flushBuffer() throws IOException{
        if(servletOutputStream!=null){
            servletOutputStream.flush();

        }
        if(printWriter!=null){
            printWriter.flush();
        }
    }

    @Override
    public void reset(){
        contentBuffer.reset();
    }

    public String getResponeData() throws IOException{
        flushBuffer();
        byte[] bytes= contentBuffer.toByteArray();
        try{
            return new String(bytes);
        }catch (Exception ex){
            return null;
        }
    }
}
