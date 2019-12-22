package com.gzl.next.document.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author GaoZhilai
 * @date 2019/9/29 14:18
 * 错误提示枚举类
 */
@Getter
public enum SysCodeEnum {
    /**
     *  通用错误码
     */
    FAILURE(HttpStatus.BAD_REQUEST, "请求失败"),
    SUCCESS(HttpStatus.OK, "请求成功"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "系统内部错误"),
    /**
     * 1000 - 1099 参数错误
     */
    PARAMETER_ERROR(HttpStatus.BAD_REQUEST, "请求参数错误"),
    /**
     * 1100 - 1199 账户, 权限相关错误
     */
    USER_NAME_OR_PASSWORD_ERROR(HttpStatus.BAD_REQUEST, "登录名或密码错误"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "请登录后再访问"),
    NOT_HAVE_PERMISSION(HttpStatus.UNAUTHORIZED, "当前账户无权访问"),
    ACCOUNT_BLOCKED(HttpStatus.UNAUTHORIZED, "账户已被封禁"),
    TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "Token错误, 请重新登录"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "登录已过期, 请重新登录"),
    LOGIN_NAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "登录名已经存在"),
    ;

    private HttpStatus status;
    private String description;

    SysCodeEnum(HttpStatus code, String description) {
        this.status = code;
        this.description = description;
    }
}