package com.github.ailitech.rest.support.interceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

public class EnhanceHttpServletResponseWrapper extends HttpServletResponseWrapper {
    private ServletOutputStream servletOutputStream;
    private ByteArrayOutputStream buffer;
    private PrintWriter printWriter;
    public EnhanceHttpServletResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        buffer=new ByteArrayOutputStream();
        servletOutputStream=new EnhanceServletOutputStream(buffer);
        printWriter=new PrintWriter(new OutputStreamWriter(buffer,"UTF-8"));
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
        buffer.reset();
    }

    public String getResponeData(String charset) throws IOException{
        flushBuffer();
        byte[] bytes=buffer.toByteArray();
        try{
            return new String(bytes,"UTF-8");
        }catch (Exception ex){
            return null;
        }
    }

    private class EnhanceServletOutputStream extends ServletOutputStream{

        private ByteArrayOutputStream outputStream;

        public EnhanceServletOutputStream(ByteArrayOutputStream value){
            outputStream=value;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
        }
    }
}
