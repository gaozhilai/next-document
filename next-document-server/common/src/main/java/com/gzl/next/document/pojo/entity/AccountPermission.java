package com.gzl.next.document.pojo.entity;

import com.gzl.next.document.pojo.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 权限实体
 */
@Data
public class AccountPermission extends BaseEntity {
    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 权限代码值
     */
    private String permissionCode;
    /**
     * 权限描述
     */
    private String description;
}
