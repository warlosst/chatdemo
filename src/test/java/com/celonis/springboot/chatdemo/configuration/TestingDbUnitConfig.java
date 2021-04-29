package com.celonis.springboot.chatdemo.configuration;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.activation.DataSource;
import javax.annotation.Resource;

@Configuration
@PropertySource("classpath:application.properties")
public class TestingDbUnitConfig {
    @Resource
    private Environment environment;

    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();

        dataSource.setDriverClass(environment.getRequiredProperty("db.driver"));
        dataSource.setJdbcUrl(environment.getRequiredProperty("db.url"));
        dataSource.setUsername(environment.getRequiredProperty("db.username"));
        dataSource.setPassword(environment.getRequiredProperty("db.password"));

        return (DataSource) dataSource;
    }

}
