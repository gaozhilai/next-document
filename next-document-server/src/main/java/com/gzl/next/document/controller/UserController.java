package com.gzl.next.document.controller;

import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import com.gzl.next.document.mapper.AccountUserMapper;
import com.gzl.next.document.pojo.entity.AccountUser;
import com.gzl.next.document.pojo.query.UserQUERY;
import com.gzl.next.document.pojo.vo.LoginVO;
import com.gzl.next.document.util.CommonResult;
import com.gzl.next.document.util.JwtUtil;
import com.gzl.next.document.util.ResultUtil;
import com.gzl.next.document.validate.SelectGroup;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author GaoZhilai
 * @date 19/11/16
 * @description No Description
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AccountUserMapper accountUserMapper;

    @PostMapping("/login")
    public CommonResult<LoginVO> login(@Validated({SelectGroup.class}) @RequestBody UserQUERY param) {
        AccountUser user = accountUserMapper.getAccountUserByLoginName(param.getLoginName());
        if (user == null) {
            throw new SysException(SysCodeEnum.USER_NAME_OR_PASSWORD_ERROR);
        }
        String salt = user.getSalt();
        String paramPassword = JwtUtil.getRealPwd(param.getPassword(), salt);
        String realPassword = user.getPassword();
        if (!StringUtils.equals(paramPassword, realPassword)) {
            throw new SysException(SysCodeEnum.USER_NAME_OR_PASSWORD_ERROR);
        }
        String token = JwtUtil.generateToken(user.getLoginName());
        LoginVO tokenVO = LoginVO.builder().token(token).build();
        return ResultUtil.renderSuccess("登录成功", tokenVO);
    }

    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequiresPermissions(logical = Logical.AND, value = {"show", "list", "modify"})
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
