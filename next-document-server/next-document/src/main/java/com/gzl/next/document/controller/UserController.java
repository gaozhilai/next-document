package com.gzl.next.document.controller;

import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import com.gzl.next.document.mapper.AccountUserMapper;
import com.gzl.next.document.pojo.dto.RolePermissionDTO;
import com.gzl.next.document.pojo.entity.AccountUser;
import com.gzl.next.document.pojo.vo.LoginVO;
import com.gzl.next.document.util.CommonResult;
import com.gzl.next.document.util.JwtUtil;
import com.gzl.next.document.util.ResultUtil;
import com.gzl.next.document.util.UserCache;
import com.gzl.next.document.validate.SelectGroup;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @author GaoZhilai
 * @date 19/11/16
 * No Description
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private AccountUserMapper accountUserMapper;

    @GetMapping("/login")
    public ResponseEntity<CommonResult<LoginVO>> login(@NotBlank @RequestParam("login_name") String loginName,
                                                       @NotBlank @RequestParam("password") String password) {
        AccountUser user = accountUserMapper.getAccountUserByLoginName(loginName);
        if (user == null) {
            throw new SysException(SysCodeEnum.USER_NAME_OR_PASSWORD_ERROR);
        }
        String salt = user.getSalt();
        String paramPassword = JwtUtil.getRealPwd(password, salt);
        String realPassword = user.getPassword();
        if (!StringUtils.equals(paramPassword, realPassword)) {
            throw new SysException(SysCodeEnum.USER_NAME_OR_PASSWORD_ERROR);
        }
        String token = JwtUtil.generateToken(user.getLoginName());
        RolePermissionDTO rolePermissionDTO = UserCache.permissionCache.getUnchecked(loginName);
        LoginVO loginVO = LoginVO
                .builder()
                .token(token)
                .roleAndPermission(rolePermissionDTO)
                .build();
        return ResultUtil.renderSuccess("登录成功", loginVO);
    }

    @RequiresRoles(logical = Logical.OR, value = {"admin"})
    @RequiresPermissions(logical = Logical.AND, value = {"show"})
    @ResponseBody
    @GetMapping("/show")
    public String showUser() {
        return "这是学生信息";
    }

    @GetMapping("/anon_show")
    public String anonShowUser() {
        return "这是学生信息";
    }
}
