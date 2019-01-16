package net.ailitech.rest.dal.orm;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages="net.ailitech.rest.dal.mapper",sqlSessionFactoryRef="defaultMasterSqlSessionFactory")
public class Register {

    private String mapperLocation="classpath:/mybatis/mapper/*.xml";

    private final String mybatisConfigLocation="classpath:/mybatis/mconfig.xml";

    @Autowired
    @Qualifier("defaultMDS")
    private DataSource dataSource;

    @Bean("defaultMasterSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean factory=new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        Resource confiResource= resolver.getResource(mybatisConfigLocation);
        factory.setConfigLocation(confiResource);

        Resource[] resource=resolver.getResources(mapperLocation);
        factory.setMapperLocations(resource);
        return factory.getObject();
    }

}
