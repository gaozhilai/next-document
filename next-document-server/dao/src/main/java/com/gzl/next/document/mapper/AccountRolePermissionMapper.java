package com.gzl.next.document.mapper;

import com.gzl.next.document.pojo.entity.AccountRolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Repository
public interface AccountRolePermissionMapper {
    /**
     * 批量插入角色全新信息
     * @param rolePermissions 角色权限信息
     * @return 插入数据条数
     */
    int insertBatchRolePermission(@Param("rolePermissions")List<AccountRolePermission> rolePermissions);
}
