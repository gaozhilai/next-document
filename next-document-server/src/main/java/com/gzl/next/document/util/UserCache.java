package com.gzl.next.document.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.gzl.next.document.pojo.dto.RolePermissionDTO;
import com.gzl.next.document.pojo.entity.AccountUser;
import com.gzl.next.document.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author GaoZhilai
 * @date 19/11/25 22:11
 * No Description
 */
@Slf4j
@Component
public class UserCache {
    private static UserService userService;

    @Autowired
    public void init(UserService userService) {
        UserCache.userService = userService;
    }

    private static CacheLoader<String, RolePermissionDTO> permissionLoader = new CacheLoader<String, RolePermissionDTO>() {
        @Override
        public RolePermissionDTO load(String key) throws Exception {
            return userService.getAvailableRoleAndPermission(key);
        }
    };
    public static LoadingCache<String, RolePermissionDTO> permissionCache = CacheBuilder.newBuilder()
            .maximumSize(500)
            .expireAfterAccess(60, TimeUnit.MINUTES)
            .recordStats()
            .build(permissionLoader);

    private static CacheLoader<String, AccountUser> userLoader = new CacheLoader<String, AccountUser>() {
        @Override
        public AccountUser load(String key) throws Exception {
            return userService.getUserByLoginName(key);
        }
    };
    public static LoadingCache<String, AccountUser> userCache = CacheBuilder.newBuilder()
            .maximumSize(500)
            .expireAfterAccess(60, TimeUnit.MINUTES)
            .recordStats()
            .build(userLoader);
}
