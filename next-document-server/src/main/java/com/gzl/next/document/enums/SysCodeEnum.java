package com.gzl.next.document.enums;

import lombok.Getter;

/**
 * @author GaoZhilai
 * Date: 2019/9/29
 * Time: 14:18
 * Description: No Description
 */
@Getter
public enum SysCodeEnum {
    /**
     *  通用错误码
     */
    FAILURE(0, "请求失败"),
    SUCCESS(1, "请求成功"),
    SERVER_ERROR(500, "系统内部错误"),
    /**
     * 1000 - 1099 参数错误
     */
    PARAMETER_ERROR(1000, "请求参数错误"),
    /**
     * 1100 - 1199 账户, 权限相关错误
     */
    USER_NAME_OR_PASSWORD_ERROR(1100, "登录名或密码错误"),
    UNAUTHORIZED(1101, "请登录后再访问"),
    NOT_HAVE_PERMISSION(1102, "当前账户无权访问"),
    ACCOUNT_BLOCKED(1103, "账户已被封禁"),
    TOKEN_ERROR(1104, "Token错误, 请重新登录"),
    ;

    private Integer code;
    private String description;

    SysCodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}