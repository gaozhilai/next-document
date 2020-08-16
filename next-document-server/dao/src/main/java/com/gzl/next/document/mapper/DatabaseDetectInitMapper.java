package com.gzl.next.document.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author GaoZhilai
 * @date 2019/12/26 21:18
 * No Description
 */
@Repository
public interface DatabaseDetectInitMapper {
    /**
     * 检测表是否存在
     * @param schemaName 数据库名
     * @param tableName 表名
     * @return 1 存在
     */
    int weatherTableExists(@Param("schemaName") String schemaName, @Param("tableName") String tableName);

    /**
     * 检测数据库是否存在
     * @param schemaName 数据库名
     * @return
     */
    int weatherDataBaseExists(@Param("schemaName") String schemaName);

    /**
     * 选择数据库
     * @param databaseName 数据库
     */
    void selectDatabase(@Param("databaseName") String databaseName);

    /**
     * 创建用户权限表
     */
    void createAccountPermission();

    /**
     * 创建禁止用户权限表
     */
    void createdAccountPermissionForbidden();

    /**
     * 创建用户角色表
     */
    void createAccountRole();

    /**
     * 创建用户角色权限表
     */
    void createAccountRolePermission();

    /**
     * 创建用户表
     */
    void createAccountUser();

    /**
     * 创建用户权限表
     */
    void createAccountUserPermission();

    /**
     * 创建用户项目表
     */
    void createAccountUserProject();

    /**
     * 创建用户角色表
     */
    void createAccountUserRole();

    /**
     * 创建菜单配置表
     */
    void createConfigModel();

    /**
     * 创建文件夹表
     */
    void createDocCategory();

    /**
     * 创建文档表
     */
    void createDocDocument();

    /**
     * 创建文档历史表
     */
    void createDocHistory();

    /**
     * 创建项目表
     */
    void createDocProject();
}
