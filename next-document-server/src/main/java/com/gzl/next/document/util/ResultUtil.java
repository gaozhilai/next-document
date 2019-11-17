package com.gzl.next.document.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GaoZhilai
 * Date: 2019/9/29
 * Time: 14:23
 * Description: No Description
 */
@Slf4j
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

    public static void renderMsgWidthPrimitiveWay(ServletResponse servletResponse, SysCodeEnum code) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try{
            response.setContentType("application/json;charset=UTF-8");
            CommonResult result = ResultUtil.renderFailure(code, null);
            response.getWriter().println(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        } catch (Exception ex) {
            log.error("返回错误信息出错, {}", ex);
        }
    }
}
