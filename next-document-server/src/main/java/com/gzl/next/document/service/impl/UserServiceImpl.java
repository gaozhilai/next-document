package com.gzl.next.document.service.impl;

import com.gzl.next.document.mapper.*;
import com.gzl.next.document.pojo.dto.RolePermissionDTO;
import com.gzl.next.document.pojo.entity.AccountPermission;
import com.gzl.next.document.pojo.entity.AccountRole;
import com.gzl.next.document.pojo.entity.AccountUser;
import com.gzl.next.document.pojo.entity.AccountUserPermission;
import com.gzl.next.document.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AccountUserMapper accountUserMapper;
    @Autowired
    private AccountRoleMapper accountRoleMapper;
    @Autowired
    private AccountPermissionMapper accountPermissionMapper;

    @Override
    public AccountUser getUserByLoginName(String loginName) {
        AccountUser user = accountUserMapper.getAccountUserByLoginName(loginName);
        return user;
    }

    @Override
    public RolePermissionDTO getAvailableRoleAndPermission(String loginName) {
        AccountUser user = accountUserMapper.getAccountUserByLoginName(loginName);
        Long userId = user.getId();
        List<AccountRole> roles = accountRoleMapper.getRoleByUserId(userId);
        List<Long> roleIds = new ArrayList<>();
        roles.stream().forEach(role -> roleIds.add(role.getId()));
        List<AccountPermission> permissions = accountPermissionMapper.getAvailablePermission(roleIds, userId);
        RolePermissionDTO rolePermissionDTO = RolePermissionDTO.builder().roles(roles).permissions(permissions).build();
        return rolePermissionDTO;
    }
}
