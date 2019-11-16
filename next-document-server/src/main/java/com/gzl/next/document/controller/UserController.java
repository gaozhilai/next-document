package com.gzl.next.document.controller;

import com.gzl.next.document.util.JwtUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

/**
 * @author GaoZhilai
 * @date 19/11/16
 * @description No Description
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam("login_name") String loginName, @RequestParam("password") String password) {
        return JwtUtil.generateToken(loginName);
    }

    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequiresPermissions(logical = Logical.AND, value = {"show", "list", "modify"})
    @ResponseBody
    @GetMapping("/show")
    public String showUser() {
        return "这是学生信息";
    }
}
