package com.gzl.next.document.pojo.entity;

import com.gzl.next.document.pojo.entity.base.BaseEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @author GaoZhilai
 * @date 19/12/29 15:07
 * No Description
 */
@Data
@Builder
public class ConfigModel extends BaseEntity {
    /**
     * 模块名称
     */
    private String modelName;
    /**
     * 模块代码
     */
    private String modelCode;
    /**
     * 模块描述
     */
    private String description;
    /**
     * 服务是否正常运行
     */
    private Boolean state;
    /**
     * 是否启用
     */
    private Boolean active;
}
