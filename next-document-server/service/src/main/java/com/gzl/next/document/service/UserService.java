package com.gzl.next.document.service;

import com.gzl.next.document.pojo.dto.RolePermissionDTO;
import com.gzl.next.document.pojo.entity.AccountUser;
import com.gzl.next.document.pojo.form.UserForm;
import com.gzl.next.document.pojo.vo.LoginVO;

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

    /**
     * 登录
     * @param loginName 登录名
     * @param password 密码
     * @return 登录结果, 包含token 和权限角色等信息
     */
    LoginVO signIn(String loginName, String password);

    /**
     * 新增, 注册用户
     * @param userForm 用户信息
     * @return 1成功
     */
    int signUp(UserForm userForm);
}
