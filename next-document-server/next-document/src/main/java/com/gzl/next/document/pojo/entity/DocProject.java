package com.gzl.next.document.pojo.entity;

import com.gzl.next.document.pojo.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 项目信息实体
 */
@Data
public class DocProject extends BaseEntity {
    /**
     * 项目名字
     */
    private String projectName;
    /**
     * 项目描述
     */
    private String description;
    /**
     * 项目创建者
     */
    private Long createdBy;
    /**
     * 项目信息更新者
     */
    private Long updatedBy;
}
