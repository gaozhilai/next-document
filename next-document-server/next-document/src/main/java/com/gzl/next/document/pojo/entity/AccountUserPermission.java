package com.gzl.next.document.pojo.entity;

import com.gzl.next.document.pojo.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 用户权限关系实体, 用户可以直接拥有权限
 */
@Data
public class AccountUserPermission extends BaseEntity {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 权限id
     */
    private Long permissionId;
}
