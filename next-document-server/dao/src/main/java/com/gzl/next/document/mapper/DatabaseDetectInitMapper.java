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

    void selectDatabase(@Param("databaseName") String databaseName);

    void createAccountPermission();

    void createdAccountPermissionForbidden();

    void createAccountRole();

    void createAccountRolePermission();

    void createAccountUser();

    void createAccountUserPermission();

    void createAccountUserRole();

    void createConfigModel();

    void createDocCategory();

    void createDocDocument();

    void createDocHistory();

    void createDocProject();
}
