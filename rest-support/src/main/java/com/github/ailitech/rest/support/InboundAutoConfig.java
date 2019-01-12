package com.github.ailitech.rest.support;

import com.github.ailitech.rest.support.interceptor.InboundInterceptor;
import com.github.ailitech.rest.support.interceptor.InboundLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InboundAutoConfig extends WebMvcConfigurerAdapter {

    private InboundInterceptor inboundInterceptor =new InboundInterceptor();
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(inboundInterceptor);
    }

    @Bean
    public FilterRegistrationBean loggingFilterRegistration(){
        FilterRegistrationBean registration=new FilterRegistrationBean();
        registration.setFilter(new InboundLoggingFilter());
        registration.setOrder(1);
        return registration;
    }
}
