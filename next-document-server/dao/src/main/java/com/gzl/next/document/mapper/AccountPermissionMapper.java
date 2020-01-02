package com.gzl.next.document.mapper;

import com.gzl.next.document.pojo.entity.AccountPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Repository
public interface AccountPermissionMapper {
    /**
     * 获取用户有效的权限
     * @param roleIds 用户拥有的角色id
     * @param userId 用户id
     * @return
     */
    List<AccountPermission> getAvailablePermission(@Param("roleIds") List<Long> roleIds,
                                                   @Param("userId") Long userId);

    /**
     * 批量插入权限
     * @param permissions 权限信息
     * @return 插入数据条数
     */
    int insertBatchPermission(@Param("permissions") List<AccountPermission> permissions);

    /**
     * 根据permissionCode获取权限信息
     * @param permissionCode 权限代码
     * @return 权限
     */
    AccountPermission getPermissionByPermissionCode(@Param("permissionCode") String permissionCode);
}
