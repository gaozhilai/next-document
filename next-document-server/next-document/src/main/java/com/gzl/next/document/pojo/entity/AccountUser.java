package com.gzl.next.document.pojo.entity;

import com.gzl.next.document.pojo.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 用户实体
 */
@Data
public class AccountUser extends BaseEntity {
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐值
     */
    private String salt;
    /**
     * 账户是否有效
     */
    private Boolean valid;
    /**
     * 头像url
     */
    private String avatar;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 0保密, 1男, 2女
     */
    private Short gender;
}
