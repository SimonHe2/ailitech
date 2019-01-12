package com.github.ailitech.rest.dal.ds;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.ailitech.rest.dal.config.DefaultMasterDruidConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DefaultMasterDruidConfig.class)
public class DefaultDataSource {

    @Autowired
    private DefaultMasterDruidConfig defaultMasterDruidConfig;

    @Bean("defaultMDS")
    public DataSource masterDS(){
        DruidDataSource ds=new DruidDataSource();
        return ds;
    }
}
