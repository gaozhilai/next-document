package com.gzl.next.document.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author GaoZhilai
 * @date 2019/12/26 21:17
 * No Description
 */
@Configuration
public class InitializerDataSourceConfig {
    @Value("${spring.datasource.database-initializer.username}")
    private String initDatabaseUserName;
    @Value("${spring.datasource.database-initializer.password}")
    private String initDatabasePassword;
    @Value("${spring.datasource.database-initializer.jdbc-url}")
    private String initDatabaseJdbcUrl;

    @Bean(name = "initializerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.my-datasource")
    public DataSource initializerDataSource() {
        DataSource initDatabaseDataSource = DataSourceBuilder.create().username(initDatabaseUserName)
                .password(initDatabasePassword).url(initDatabaseJdbcUrl).build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(initDatabaseDataSource);
        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS next_document CHARACTER SET UTF8MB4");
        return DataSourceBuilder.create().build();
    }
}
