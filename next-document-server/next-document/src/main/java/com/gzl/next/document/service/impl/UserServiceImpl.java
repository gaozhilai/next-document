package com.gzl.next.document.service.impl;

import com.gzl.next.document.enums.CommonStatusEnum;
import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import com.gzl.next.document.mapper.*;
import com.gzl.next.document.pojo.dto.RolePermissionDTO;
import com.gzl.next.document.pojo.entity.AccountPermission;
import com.gzl.next.document.pojo.entity.AccountRole;
import com.gzl.next.document.pojo.entity.AccountUser;
import com.gzl.next.document.pojo.form.UserForm;
import com.gzl.next.document.pojo.vo.LoginVO;
import com.gzl.next.document.service.UserService;
import com.gzl.next.document.util.JwtUtil;
import com.gzl.next.document.util.UserCache;
import com.gzl.next.document.util.UserUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<AccountRole> accountRoles = accountRoleMapper.getRoleByUserId(userId);
        List<Long> roleIds = new ArrayList<>();
        accountRoles.stream().forEach(role -> roleIds.add(role.getId()));
        List<AccountPermission> accountPermissions = accountPermissionMapper.getAvailablePermission(roleIds, userId);
        Set<String> roles = accountRoles
                .stream()
                .map(AccountRole::getRoleName)
                .collect(Collectors.toSet());
        Set<String> permissions = accountPermissions
                .stream()
                .map(AccountPermission::getPermissionName)
                .collect(Collectors.toSet());
        RolePermissionDTO rolePermissionDTO = RolePermissionDTO.builder().roles(roles).permissions(permissions).build();
        return rolePermissionDTO;
    }

    @Override
    public LoginVO signIn(String loginName, String password) {
        AccountUser user = accountUserMapper.getAccountUserByLoginName(loginName);
        if (user == null) {
            throw new SysException(SysCodeEnum.USER_NAME_OR_PASSWORD_ERROR);
        }
        String salt = user.getSalt();
        String paramPassword = UserUtil.getRealPwd(password, salt);
        String realPassword = user.getPassword();
        if (!StringUtils.equals(paramPassword, realPassword)) {
            throw new SysException(SysCodeEnum.USER_NAME_OR_PASSWORD_ERROR);
        }
        String token = JwtUtil.generateToken(user.getLoginName(), salt);
        RolePermissionDTO rolePermissionDTO = UserCache.permissionCache.getUnchecked(loginName);
        LoginVO loginVO = LoginVO
                .builder()
                .token(token)
                .roleAndPermission(rolePermissionDTO)
                .build();
        return loginVO;
    }

    @Override
    public int signUp(UserForm userForm) {
        AccountUser accountUserByLoginName = accountUserMapper.getAccountUserByLoginName(userForm.getLoginName());
        if (accountUserByLoginName != null) {
            throw new SysException(SysCodeEnum.LOGIN_NAME_ALREADY_EXIST);
        }
        String salt = RandomStringUtils.randomAlphanumeric(64);
        String realPwd = UserUtil.getRealPwd(userForm.getPassword(), salt);
        int res = accountUserMapper.addUser(userForm.getLoginName(), userForm.getNickname(), realPwd,
                salt, CommonStatusEnum.VALID.getCode(), userForm.getAvatar(), userForm.getPhone(), userForm.getEmail(),
                userForm.getGender());
        return res;
    }
}
