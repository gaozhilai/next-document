package com.gzl.next.document.shiro.realm;

import com.gzl.next.document.shiro.token.JwtToken;
import com.gzl.next.document.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author GaoZhilai
 * @date 19/11/16
 * @description No Description
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("账户权限验证方法");
        String loginName = JwtUtil.getClaim(principalCollection.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // TODO 根据登录名获取拥有的角色和权限
        String role = "admin";
        String rolePermission = "show";
        String permission = "list";
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        //需要将 role, permission 封装到 Set 作为 info.setRoles(), info.setStringPermissions() 的参数
        roleSet.add(role);
        permissionSet.add(rolePermission);
        permissionSet.add(permission);
        //设置该用户拥有的角色和权限
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("账户登录名密码验证方法");
        String token = (String) authenticationToken.getCredentials();
        String loginName = JwtUtil.validateToken(token);
        // TODO 根据登录名查询数据库用户信息, 判断账户是否存在, 可用状态
        return new SimpleAuthenticationInfo(token, token, "JwtRealm");
    }
}
