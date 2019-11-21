package com.gzl.next.document.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author GaoZhilai
 * @date 2019/9/29 14:22
 * 通用返回结果
 */
@ApiModel(value = "com.gzl.next.document.util.CommonResult", description = "接口通用返回结果结构")
@Data
public final class CommonResult<T> {
    @ApiModelProperty(value = "提示信息")
    private String msg;
    @ApiModelProperty(value = "返回结果实际数据")
    private T data;
}