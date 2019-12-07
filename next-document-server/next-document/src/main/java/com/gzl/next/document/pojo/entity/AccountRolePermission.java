package com.gzl.next.document.pojo.entity;

import com.gzl.next.document.pojo.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 角色权限关系实体
 */
@Data
public class AccountRolePermission extends BaseEntity {
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 角色拥有的权限id
     */
    private Long permissionId;
}
