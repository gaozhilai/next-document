package com.gzl.next.document.service.impl;

import com.gzl.next.document.mapper.AccountUserMapper;
import com.gzl.next.document.pojo.dto.RolePermissionDTO;
import com.gzl.next.document.pojo.entity.AccountUser;
import com.gzl.next.document.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AccountUserMapper accountUserMapper;

    @Override
    public AccountUser getUserByLoginName(String loginName) {
        AccountUser user = accountUserMapper.getAccountUserByLoginName(loginName);
        return user;
    }

    @Override
    public RolePermissionDTO getAvailableRoleAndPermission(String loginName) {
        AccountUser user = accountUserMapper.getAccountUserByLoginName(loginName);
        Long userId = user.getId();
        return null;
    }
}
