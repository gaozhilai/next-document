package com.gzl.next.document.controller;

import com.gzl.next.document.pojo.form.UserFORM;
import com.gzl.next.document.pojo.vo.LoginVO;
import com.gzl.next.document.service.UserService;
import com.gzl.next.document.util.CommonResult;
import com.gzl.next.document.util.ResultUtil;
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
    private UserService userService;

    @GetMapping("/sign_in")
    public ResponseEntity<CommonResult<LoginVO>> login(@NotBlank @RequestParam("login_name") String loginName,
                                                       @NotBlank @RequestParam("password") String password) {
        LoginVO loginVO = userService.signIn(loginName, password);
        return ResultUtil.renderSuccess("登录成功", loginVO);
    }

    @PostMapping("/sign_up")
    public ResponseEntity<CommonResult> signUp(@Validated @RequestBody UserFORM userFORM) {
        int res = userService.signUp(userFORM);
        if (res > 0) {
            return ResultUtil.renderSuccess("用户注册成功");
        } else {
            return ResultUtil.renderSuccess("用户注册失败");
        }
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
