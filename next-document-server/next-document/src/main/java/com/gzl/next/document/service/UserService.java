package com.gzl.next.document.service;

import com.gzl.next.document.pojo.dto.RolePermissionDTO;
import com.gzl.next.document.pojo.entity.AccountUser;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 用户相关操作
 */
public interface UserService {
    /**
     * 根据登录名获取用户信息
     * @param loginName 登录名
     * @return 用户
     */
    AccountUser getUserByLoginName(String loginName);

    /**
     * 通过登录名获取用户有效的角色和权限
     * @param loginName
     * @return
     */
    RolePermissionDTO getAvailableRoleAndPermission(String loginName);
}
