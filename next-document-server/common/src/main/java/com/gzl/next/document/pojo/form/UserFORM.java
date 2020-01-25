package com.gzl.next.document.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author GaoZhilai
 * @date 19/12/22 12:37
 * 新增, 注册用户表单对象
 */
@Data
public class UserFORM {
    @NotBlank
    private String loginName;
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
    private String avatar;
    private String phone;
    private String email;
    private Short gender;
}
