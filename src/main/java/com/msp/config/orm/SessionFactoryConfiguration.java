package com.msp.config.orm;

import java.io.IOException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import org.mybatis.spring.SqlSessionFactoryBean;

@Configuration
public class SessionFactoryConfiguration {
    private SqlSessionFactoryBean sessionFactory;

    @Resource(name = "dataSource", type = DataSource.class)
    private DataSource dataSource;

    @Value("${mybatis.config-location}")
    private String configPath;

    @Value("${mybatis.mapper-locations}")
    private String mapperPaths;

    @Value("${mybatis.entityPackages}")
    private String entityPackages;

    private ResourcePatternResolver resourcePatternResolver;

    @Bean(name = {"sessionFactory"}, autowire = Autowire.NO)
    @Scope(scopeName = "singleton")
    public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
        sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(new ClassPathResource(configPath));

        resourcePatternResolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resourcePatternResolver.getResources(mapperPaths));
        sessionFactory.setTypeAliasesPackage(entityPackages);
        return sessionFactory;
    }
}
