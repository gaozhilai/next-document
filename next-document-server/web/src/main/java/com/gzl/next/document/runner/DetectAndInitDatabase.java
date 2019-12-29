package com.gzl.next.document.runner;

import com.gzl.next.document.mapper.DatabaseDetectInitMapper;
import com.gzl.next.document.service.DatabaseDetectInitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author GaoZhilai
 * @date 2019/12/26 21:17
 * No Description
 */
@Component
@Order(1)
@Slf4j
public class DetectAndInitDatabase implements ApplicationRunner {
    @Autowired
    private DatabaseDetectInitService databaseDetectInitService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        databaseDetectInitService.detectInitDatabase();
    }
}
