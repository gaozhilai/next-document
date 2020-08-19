package com.gzl.next.document.service.impl;

import com.gzl.next.document.mapper.*;
import com.gzl.next.document.pojo.entity.*;
import com.gzl.next.document.service.DatabaseDetectInitService;
import com.gzl.next.document.util.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private DatabaseDetectInitMapper databaseDetectInitMapper;
    @Autowired
    private ConfigModelMapper configModelMapper;
    @Autowired
    private AccountUserMapper accountUserMapper;
    @Autowired
    private AccountRoleMapper accountRoleMapper;
    @Autowired
    private AccountPermissionMapper accountPermissionMapper;
    @Autowired
    private AccountRolePermissionMapper accountRolePermissionMapper;
    @Autowired
    private AccountUserRoleMapper accountUserRoleMapper;

    @Override
    public boolean detectInitDatabase() {
        int databaseExists = databaseDetectInitMapper.weatherTableExists("next_document",
                "account_user");
        // account_user表不存在视为数据库需要初始化表结构
        if (databaseExists == 0) {
            // 数据库不存在创建数据库, 创建表
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
            this.initConfigModelData();
            this.initAccountRoleData();
            this.initAccountPermissionData();
            this.initAccountRolePermissionData();
            this.initAccountUserData();
            this.initUserRoleData();
            log.info("初始化数据库表结构以及必要数据完毕");
            return true;
        }
        return false;
    }

    @Override
    public void initConfigModelData() {
        ConfigModel drawingBoard = new ConfigModel();
        drawingBoard.setModelName("画板");
        drawingBoard.setModelCode("drawing_board");
        drawingBoard.setDescription("同步画板模块");
        drawingBoard.setState(true);
        drawingBoard.setActive(true);
        ConfigModel vote = new ConfigModel();
        vote.setModelName("投票");
        vote.setModelCode("vote");
        vote.setDescription("投票模块");
        vote.setState(true);
        vote.setActive(true);
        ConfigModel instantMessaging = new ConfigModel();
        instantMessaging.setModelName("即时通讯");
        instantMessaging.setModelCode("instant_messaging");
        instantMessaging.setDescription("即时通讯模块");
        instantMessaging.setState(true);
        instantMessaging.setActive(true);
        List<ConfigModel> configModels = Arrays.asList(new ConfigModel[]{drawingBoard, vote, instantMessaging});
        configModelMapper.insertBatchConfigModel(configModels);
    }

    @Override
    public void initAccountUserData() {
        String password = "123456";
        String salt = RandomStringUtils.randomAlphanumeric(64);
        String realPassword = EncryptionUtil.getRealPwd(password, salt);
        AccountUser accountUser = new AccountUser();
        accountUser.setLoginName("admin");
        accountUser.setNickname("管理员");
        accountUser.setPassword(realPassword);
        accountUser.setSalt(salt);
        accountUser.setValid(true);
        accountUserMapper.insertBatchUser(Arrays.asList(accountUser));
    }

    @Override
    public void initAccountRoleData() {
        AccountRole role = new AccountRole();
        role.setRoleName("管理员");
        role.setRoleCode("admin");
        role.setDescription("管理员角色");
        accountRoleMapper.insertBatchRole(Arrays.asList(role));
    }

    @Override
    public void initAccountPermissionData() {
        AccountPermission managerPermission = new AccountPermission();
        managerPermission.setPermissionName("管理员权限");
        managerPermission.setPermissionCode("manager");
        managerPermission.setDescription("管理员权限");
        accountPermissionMapper.insertBatchPermission(Arrays.asList(managerPermission));
    }

    @Override
    public void initAccountRolePermissionData() {
        AccountRole admin = accountRoleMapper.getRoleByRoleCode("admin");
        AccountPermission manager = accountPermissionMapper.getPermissionByPermissionCode("manager");
        AccountRolePermission rolePermission = new AccountRolePermission();
        rolePermission.setRoleId(admin.getId());
        rolePermission.setPermissionId(manager.getId());
        accountRolePermissionMapper.insertBatchRolePermission(Arrays.asList(rolePermission));
    }

    @Override
    public void initUserRoleData() {
        AccountUser adminUser = accountUserMapper.getAccountUserByLoginName("admin");
        AccountRole adminRole = accountRoleMapper.getRoleByRoleCode("admin");
        AccountUserRole userRole = new AccountUserRole();
        userRole.setUserId(adminUser.getId());
        userRole.setRoleId(adminRole.getId());
        accountUserRoleMapper.insertBatchUserRole(Arrays.asList(userRole));
    }
}
