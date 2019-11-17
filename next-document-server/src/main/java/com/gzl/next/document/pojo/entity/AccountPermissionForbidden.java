package com.gzl.next.document.pojo.entity;

import com.gzl.next.document.pojo.entity.base.BaseEntity;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 禁止的权限. 如赋予用户角色, 额外指定禁止某个角色
 */
public class AccountPermissionForbidden extends BaseEntity {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 权限id
     */
    private Long permissionId;
}
