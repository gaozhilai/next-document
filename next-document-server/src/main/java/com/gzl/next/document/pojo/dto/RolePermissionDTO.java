package com.gzl.next.document.pojo.dto;

import com.gzl.next.document.pojo.entity.AccountPermission;
import com.gzl.next.document.pojo.entity.AccountRole;
import lombok.Data;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 包含指定用户有效的权限和角色
 */
@Data
public class RolePermissionDTO {
    List<AccountRole> roles;
    List<AccountPermission> permissions;
}
