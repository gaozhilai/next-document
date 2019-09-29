package com.gzl.next.document.exception;

import com.gzl.next.document.enums.SysCodeEnum;
import lombok.Data;

/**
 * Author: GaoZhilai
 * Date: 2019/9/29
 * Time: 14:15
 * Description: No Description
 */
@Data
public class SysException extends RuntimeException {
    private int code;
    private SysCodeEnum sysCodeEnum;

    public SysException(SysCodeEnum code) {
        super(code.getDescription());
        this.code = code.getCode();
        this.sysCodeEnum = code;
    }
}