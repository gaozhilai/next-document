package com.gzl.next.document.pojo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 包含指定用户有效的权限和角色
 */
@Data
@Builder
public class RolePermissionDTO {
    private Set<String> roles;
    private Set<String> permissions;
}
