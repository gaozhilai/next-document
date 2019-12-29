package com.gzl.next.document.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author GaoZhilai
 * @date 2019/12/26 21:17
 * No Description
 */
@Configuration
public class InitializerDataSourceConfig {
    @Bean(name = "initializerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.database-initializer")
    public DataSource initializerDataSource() {
        return DataSourceBuilder.create().build();
    }
}
