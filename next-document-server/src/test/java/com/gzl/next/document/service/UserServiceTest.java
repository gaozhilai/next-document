package com.gzl.next.document.service;

import com.gzl.next.document.base.BaseTest;
import com.gzl.next.document.pojo.dto.RolePermissionDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
public class UserServiceTest extends BaseTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetAvailableRoleAndPermission() {
        RolePermissionDTO rolePermissionDTO = userService.getAvailableRoleAndPermission("gzl");
        log.info("获取到角色权限信息, {}", rolePermissionDTO);
    }
}
