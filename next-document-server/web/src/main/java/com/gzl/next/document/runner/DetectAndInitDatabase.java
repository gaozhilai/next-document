package com.gzl.next.document.runner;

import com.gzl.next.document.NextDocumentServerApplication;
import com.gzl.next.document.service.DatabaseDetectInitService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author GaoZhilai
 * @date 2019/12/26 21:17
 * No Description
 */
@Component
@Order(1)
@Slf4j
public class DetectAndInitDatabase implements ApplicationRunner, ApplicationContextAware {
    @Autowired
    private DatabaseDetectInitService databaseDetectInitService;

    private ApplicationContext context;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        boolean initialized = databaseDetectInitService.detectInitDatabase();
        if (initialized) {
            // 如果表结构第一次初始化完毕, 重启当前项目, 防止可能存在的数据库连接池问题
            log.info("首次初始化表结构以及必要数据完毕, 即将重启项目, 避免潜在问题");
            ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) this.context;
            configurableApplicationContext.close();
            SpringApplication.run(NextDocumentServerApplication.class, NextDocumentServerApplication.getArgs());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
