package com.gzl.next.document.util;

import com.gzl.next.document.enums.SysCodeEnum;

/**
 * Author: GaoZhilai
 * Date: 2019/9/29
 * Time: 14:23
 * Description: No Description
 */
public class ResultUtil {
    public static <T> CommonResult<T> renderFailure(SysCodeEnum code, T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(code.getCode());
        result.setData(data);
        result.setMsg(code.getDescription());
        return result;
    }

    public static CommonResult renderFailure(String msg) {
        CommonResult result = new CommonResult();
        result.setCode(SysCodeEnum.FAILURE.getCode());
        result.setData(null);
        result.setMsg(msg);
        return result;
    }

    public static CommonResult renderFailure(SysCodeEnum code) {
        CommonResult result = new CommonResult();
        result.setCode(code.getCode());
        result.setData(null);
        result.setMsg(code.getDescription());
        return result;
    }

    public static CommonResult renderSuccess() {
        CommonResult result = new CommonResult();
        result.setCode(SysCodeEnum.SUCCESS.getCode());
        result.setMsg(SysCodeEnum.SUCCESS.getDescription());
        return result;
    }

    public static CommonResult renderSuccess(String msg) {
        CommonResult result = new CommonResult();
        result.setCode(SysCodeEnum.SUCCESS.getCode());
        result.setMsg(msg);
        return result;
    }

    public static <T> CommonResult<T> renderSuccess(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(SysCodeEnum.SUCCESS.getCode());
        result.setMsg(SysCodeEnum.SUCCESS.getDescription());
        result.setData(data);
        return result;
    }

    public static <T> CommonResult<T> renderSuccess(String msg, T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(SysCodeEnum.SUCCESS.getCode());
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
