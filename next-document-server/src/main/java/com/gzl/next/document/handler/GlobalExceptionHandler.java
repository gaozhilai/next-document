package com.gzl.next.document.handler;

import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import com.gzl.next.document.util.CommonResult;
import com.gzl.next.document.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: GaoZhilai
 * Date: 2019/9/29
 * Time: 15:59
 * Description: No Description
 */
@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public CommonResult handleException(Exception e) {
        if (e instanceof SysException) {
            SysException sysException = (SysException) e;
            return ResultUtil.renderFailure(sysException.getSysCodeEnum(), null);
        }
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException validException = (MethodArgumentNotValidException) e;
            List<FieldError> errors = validException.getBindingResult().getFieldErrors();
            StringBuffer errorReason = new StringBuffer("请求参数错误:");
            for (FieldError error :
                    errors) {
                String errotField = error.getField();
                String defaultMessage = error.getDefaultMessage();
                errorReason.append("参数").append(getSnakeCase(errotField)).append(defaultMessage);
            }
            return ResultUtil.renderFailure(SysCodeEnum.PARAMETER_ERROR, errorReason.toString());
        }
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return ResultUtil.renderFailure(e.getMessage());
        }
        e.printStackTrace();
        return ResultUtil.renderFailure(SysCodeEnum.SERVER_ERROR, null);
    }

    private String getSnakeCase(String param) {
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}