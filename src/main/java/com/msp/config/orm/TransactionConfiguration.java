package com.msp.config.orm;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@EnableTransactionManagement
public class TransactionConfiguration {
    private DataSourceTransactionManager transactionManager;

    @Resource(name = "dataSource", type = DataSource.class)
    private DataSource dataSource;

    public DataSourceTransactionManager transactionManager() {
        transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }
}
