package com.github.ailitech.rest.support;

import com.github.ailitech.rest.support.interceptor.HttpIncomeFilter;
import com.github.ailitech.rest.support.interceptor.ReplaceHttpServletRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SupportAutoConfig extends WebMvcConfigurerAdapter {

    private HttpIncomeFilter httpIncomeFilter=new HttpIncomeFilter();
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(httpIncomeFilter);
    }

    @Bean
    public FilterRegistrationBean enhanceHttpServletRequestWrapperRegistration(){
        FilterRegistrationBean enHanceBean=new FilterRegistrationBean();
        enHanceBean.setFilter(new ReplaceHttpServletRequestFilter());
        enHanceBean.setOrder(1);
        return enHanceBean;
    }
}
