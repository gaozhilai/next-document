package com.gzl.next.document.exception;

import com.gzl.next.document.enums.SysCodeEnum;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author GaoZhilai
 * @date 2019/9/29 14:15
 * 系统自定义异常
 */
@Data
public class SysException extends RuntimeException {
    private HttpStatus status;
    private SysCodeEnum sysCodeEnum;

    public SysException(SysCodeEnum code) {
        super(code.getDescription());
        this.status = code.getStatus();
        this.sysCodeEnum = code;
    }
}