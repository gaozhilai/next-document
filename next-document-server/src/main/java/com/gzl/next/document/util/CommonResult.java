package com.gzl.next.document.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author: GaoZhilai
 * Date: 2019/9/29
 * Time: 14:22
 * Description: No Description
 */
@ApiModel(value = "com.gzl.next.document.util.CommonResult", description = "接口通用返回结果结构")
@Data
public final class CommonResult<T> {
    @ApiModelProperty(value = "1成功, 其他code值均为失败")
    private Integer code;
    @ApiModelProperty(value = "提示信息")
    private String msg;
    @ApiModelProperty(value = "返回结果实际数据")
    private T data;
}