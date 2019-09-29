package com.gzl.next.document.enums;

import lombok.Getter;

/**
 * Author: GaoZhilai
 * Date: 2019/9/29
 * Time: 14:18
 * Description: No Description
 */
@Getter
public enum SysCodeEnum {
    FAILURE(0, "请求失败"),
    SUCCESS(1, "请求成功"),
    UNAUTHORIZED(402, "未授权的访问"),
    SERVER_ERROR(500, "系统内部错误"),
    USER_NAME_OR_PASSWORD_ERROR(9000, "登录名或密码错误"),
    ;

    private Integer code;
    private String description;

    SysCodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}