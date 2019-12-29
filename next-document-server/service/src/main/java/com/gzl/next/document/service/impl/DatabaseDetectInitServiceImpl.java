package com.gzl.next.document.service.impl;

import com.gzl.next.document.mapper.ConfigModelMapper;
import com.gzl.next.document.mapper.DatabaseDetectInitMapper;
import com.gzl.next.document.pojo.entity.ConfigModel;
import com.gzl.next.document.service.DatabaseDetectInitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/12/26 22:31
 * No Description
 */
@Slf4j
@Service
public class DatabaseDetectInitServiceImpl implements DatabaseDetectInitService {
    @Autowired
    private DataSource initializerDataSource;
    @Autowired
    private DatabaseDetectInitMapper databaseDetectInitMapper;
    @Autowired
    private ConfigModelMapper configModelMapper;

    @Override
    public void detectInitDatabase() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(initializerDataSource);
        Integer databaseExists = jdbcTemplate.query(
                "SELECT COUNT(*) FROM information_schema.SCHEMATA WHERE schema_name = 'next_document'",
                (rs, rowNum) -> rs.getInt(1)).get(0);
        if (databaseExists > 0) {
            databaseDetectInitMapper.selectDatabase("next_document");
            // 数据库已经存在, 分别检测表是否存在
            int accountPermission = databaseDetectInitMapper.weatherTableExists("next_document",
                    "account_permission");
            if (accountPermission == 0) {
                databaseDetectInitMapper.createAccountPermission();
            }
            int accountPermissionForbidden = databaseDetectInitMapper.weatherTableExists("next_document",
                    "account_permission_forbidden");
            if (accountPermissionForbidden == 0) {
                databaseDetectInitMapper.createdAccountPermissionForbidden();
            }
            int accountRole = databaseDetectInitMapper.weatherTableExists("next_document",
                    "account_role");
            if (accountRole == 0) {
                databaseDetectInitMapper.createAccountRole();
            }
            int accountRolePermission = databaseDetectInitMapper.weatherTableExists("next_document",
                    "account_role_permission");
            if (accountRolePermission == 0) {
                databaseDetectInitMapper.createAccountRolePermission();
            }
            int accountUser = databaseDetectInitMapper.weatherTableExists("next_document",
                    "account_user");
            if (accountUser == 0) {
                databaseDetectInitMapper.createAccountUser();
            }
            int accountUserPermission = databaseDetectInitMapper.weatherTableExists("next_document",
                    "account_user_permission");
            if (accountUserPermission == 0) {
                databaseDetectInitMapper.createAccountUserPermission();
            }
            int accountUserRole = databaseDetectInitMapper.weatherTableExists("next_document",
                    "account_user_role");
            if (accountUserRole == 0) {
                databaseDetectInitMapper.createAccountUserRole();
            }
            int configModel = databaseDetectInitMapper.weatherTableExists("next_document",
                    "config_model");
            if (configModel == 0) {
                databaseDetectInitMapper.createConfigModel();
                this.initConfigModelData();
            }
            int docCategory = databaseDetectInitMapper.weatherTableExists("next_document",
                    "doc_category");
            if (docCategory == 0) {
                databaseDetectInitMapper.createDocCategory();
            }
            int docDocument = databaseDetectInitMapper.weatherTableExists("next_document",
                    "doc_document");
            if (docDocument == 0) {
                databaseDetectInitMapper.createDocDocument();
            }
            int docHistory = databaseDetectInitMapper.weatherTableExists("next_document",
                    "doc_history");
            if (docHistory == 0) {
                databaseDetectInitMapper.createDocHistory();
            }
            int docProject = databaseDetectInitMapper.weatherTableExists("next_document",
                    "doc_project");
            if (docProject == 0) {
                databaseDetectInitMapper.createDocProject();
            }
        } else {
            // 数据库不存在创建数据库, 创建表
            jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS next_document CHARACTER SET UTF8MB4");
            databaseDetectInitMapper.selectDatabase("next_document");
            databaseDetectInitMapper.createAccountPermission();
            databaseDetectInitMapper.createdAccountPermissionForbidden();
            databaseDetectInitMapper.createAccountRole();
            databaseDetectInitMapper.createAccountRolePermission();
            databaseDetectInitMapper.createAccountUser();
            databaseDetectInitMapper.createAccountUserPermission();
            databaseDetectInitMapper.createAccountUserRole();
            databaseDetectInitMapper.createConfigModel();
            databaseDetectInitMapper.createDocCategory();
            databaseDetectInitMapper.createDocDocument();
            databaseDetectInitMapper.createDocHistory();
            databaseDetectInitMapper.createDocProject();
        }
        log.info("初始化数据库表结构以及必要数据完毕");
    }

    @Override
    public void initConfigModelData() {
        ConfigModel drawingBoard = ConfigModel.builder().modelName("画板").modelCode("drawing_board")
                .description("同步画板模块").state(true).active(true).build();
        ConfigModel vote = ConfigModel.builder().modelName("投票").modelCode("vote")
                .description("投票模块").state(true).active(true).build();
        ConfigModel instantMessaging = ConfigModel.builder().modelName("即时通讯").modelCode("instant_messaging")
                .description("及时通讯模块").state(true).active(true).build();
        List<ConfigModel> configModels = Arrays.asList(new ConfigModel[]{drawingBoard, vote, instantMessaging});
        configModelMapper.insertBatchConfigModel(configModels);
    }
}
