package com.msp.config.orm;

import java.beans.PropertyVetoException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowire;

@Configuration
public class DataSourceConfiguration {
    private ComboPooledDataSource dataSource;

    @Value("${jdbc.connection.driverClass}")
    private String driverClass;

    @Value("${jdbc.connection.jdbcUrl}")
    private String jdbcUrl;

    @Value("${jdbc.connection.user}")
    private String user;

    @Value("${jdbc.connection.password}")
    private String password;

    private boolean autoCommit;

    private int maxPoolSize;

    private int minPoolSize;

    @Bean(name = {"dataSource"}, autowire = Autowire.NO)
    @Scope(scopeName = "singleton")
    public ComboPooledDataSource comboPooledDataSource() throws PropertyVetoException {
        maxPoolSize = 30;
        minPoolSize = 3;
        autoCommit = false;

        dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setAutoCommitOnClose(autoCommit);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMinPoolSize(minPoolSize);
        return dataSource;
    }
}
