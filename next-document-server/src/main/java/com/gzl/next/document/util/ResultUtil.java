package com.gzl.next.document.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gzl.next.document.enums.SysCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

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
    public static <T> ResponseEntity<CommonResult<T>> renderFailure(SysCodeEnum code, T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setData(data);
        result.setMsg(code.getDescription());
        return new ResponseEntity<>(result, code.getStatus());
    }

    public static ResponseEntity<CommonResult> renderFailure(String msg) {
        CommonResult result = new CommonResult();
        result.setMsg(msg);
        return new ResponseEntity<>(result, SysCodeEnum.FAILURE.getStatus());
    }

    public static ResponseEntity<CommonResult> renderFailure(SysCodeEnum code) {
        CommonResult result = new CommonResult();
        result.setMsg(code.getDescription());
        return new ResponseEntity<>(result, code.getStatus());
    }

    public static ResponseEntity<CommonResult> renderSuccess() {
        CommonResult result = new CommonResult();
        result.setMsg(SysCodeEnum.SUCCESS.getDescription());
        return new ResponseEntity<>(result, SysCodeEnum.SUCCESS.getStatus());
    }

    public static ResponseEntity<CommonResult> renderSuccess(String msg) {
        CommonResult result = new CommonResult();
        result.setMsg(msg);
        return new ResponseEntity<>(result, SysCodeEnum.SUCCESS.getStatus());
    }

    public static <T> ResponseEntity<CommonResult<T>> renderSuccess(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setMsg(SysCodeEnum.SUCCESS.getDescription());
        result.setData(data);
        return new ResponseEntity<>(result, SysCodeEnum.SUCCESS.getStatus());
    }

    public static <T> ResponseEntity<CommonResult<T>> renderSuccess(String msg, T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setMsg(msg);
        result.setData(data);
        return new ResponseEntity<>(result, SysCodeEnum.SUCCESS.getStatus());
    }

    public static void renderMsgWidthPrimitiveWay(ServletResponse servletResponse, SysCodeEnum code) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try{
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(code.getStatus().value());
            ResponseEntity<CommonResult> result = ResultUtil.renderFailure(code);
            response.getWriter().println(JSON.toJSONString(result.getBody(), SerializerFeature.WriteMapNullValue));
        } catch (Exception ex) {
            log.error("返回错误信息出错, {}", ex);
        }
    }
}
