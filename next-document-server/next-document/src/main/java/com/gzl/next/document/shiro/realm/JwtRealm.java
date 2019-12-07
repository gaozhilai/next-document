package com.gzl.next.document.shiro.realm;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import com.gzl.next.document.pojo.dto.RolePermissionDTO;
import com.gzl.next.document.pojo.entity.AccountPermission;
import com.gzl.next.document.pojo.entity.AccountRole;
import com.gzl.next.document.pojo.entity.AccountUser;
import com.gzl.next.document.service.UserService;
import com.gzl.next.document.shiro.token.JwtToken;
import com.gzl.next.document.util.JwtUtil;
import com.gzl.next.document.util.ResultUtil;
import com.gzl.next.document.util.UserCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author GaoZhilai
 * @date 19/11/16
 * @description No Description
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("账户权限验证方法");
        String loginName = JwtUtil.getClaim(principalCollection.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // TODO 根据登录名获取拥有的角色和权限, 权限Service方法需要考虑周全
        RolePermissionDTO availableRoleAndPermission = UserCache.permissionCache.getUnchecked(loginName);
        if (availableRoleAndPermission.getRoles() != null) {
            info.setRoles(availableRoleAndPermission.getRoles());
        }
        if (availableRoleAndPermission.getPermissions() != null) {
            info.setStringPermissions(availableRoleAndPermission.getPermissions());
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("账户登录名密码验证方法");
        String token = (String) authenticationToken.getCredentials();
        String loginName = JwtUtil.validateToken(token);
        if (loginName == null) {
            // token验证失败
            throw new AuthenticationException(new SysException(SysCodeEnum.TOKEN_ERROR));
        }
        AccountUser user = UserCache.userCache.getUnchecked(loginName);
        if (!user.getValid()) {
            throw new AuthenticationException(new SysException(SysCodeEnum.ACCOUNT_BLOCKED));
        }
        JwtToken jwtToken = (JwtToken) authenticationToken;
        ServletRequest request = jwtToken.getRequest();
        request.setAttribute("userId", user.getId());
        return new SimpleAuthenticationInfo(token, token, "JwtRealm");
    }
}