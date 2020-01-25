package com.gzl.next.document.service.impl;

import com.gzl.next.document.enums.CommonStatusEnum;
import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import com.gzl.next.document.mapper.*;
import com.gzl.next.document.pojo.dto.RolePermissionDTO;
import com.gzl.next.document.pojo.entity.AccountPermission;
import com.gzl.next.document.pojo.entity.AccountRole;
import com.gzl.next.document.pojo.entity.AccountUser;
import com.gzl.next.document.pojo.form.UserFORM;
import com.gzl.next.document.pojo.vo.LoginVO;
import com.gzl.next.document.service.UserService;
import com.gzl.next.document.util.JwtUtil;
import com.gzl.next.document.util.UserCache;
import com.gzl.next.document.util.EncryptionUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
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
        accountRoles.forEach(role -> roleIds.add(role.getId()));
        if (CollectionUtils.isEmpty(roleIds)) {
            return RolePermissionDTO.builder().build();
        }
        List<AccountPermission> accountPermissions = accountPermissionMapper.getAvailablePermission(roleIds, userId);
        Set<String> roles = accountRoles
                .stream()
                .map(AccountRole::getRoleCode)
                .collect(Collectors.toSet());
        Set<String> permissions = accountPermissions
                .stream()
                .map(AccountPermission::getPermissionCode)
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
        String paramPassword = EncryptionUtil.getRealPwd(password, salt);
        String realPassword = user.getPassword();
        if (!StringUtils.equals(paramPassword, realPassword)) {
            throw new SysException(SysCodeEnum.USER_NAME_OR_PASSWORD_ERROR);
        }
        String token = JwtUtil.generateToken(user.getLoginName(), salt, user.getPassword());
        RolePermissionDTO rolePermissionDTO = UserCache.permissionCache.getUnchecked(loginName);
        LoginVO loginVO = LoginVO
                .builder()
                .token(token)
                .roleAndPermission(rolePermissionDTO)
                .build();
        return loginVO;
    }

    @Override
    public int signUp(UserFORM userFORM) {
        AccountUser accountUserByLoginName = accountUserMapper.getAccountUserByLoginName(userFORM.getLoginName());
        if (accountUserByLoginName != null) {
            throw new SysException(SysCodeEnum.LOGIN_NAME_ALREADY_EXIST);
        }
        String salt = RandomStringUtils.randomAlphanumeric(64);
        String realPwd = EncryptionUtil.getRealPwd(userFORM.getPassword(), salt);
        int res = accountUserMapper.addUser(userFORM.getLoginName(), userFORM.getNickname(), realPwd,
                salt, CommonStatusEnum.VALID.getCode(), userFORM.getAvatar(), userFORM.getPhone(), userFORM.getEmail(),
                userFORM.getGender());
        return res;
    }
}
