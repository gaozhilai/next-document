package com.gzl.next.document.pojo.entity.base;

import lombok.Data;

import java.util.Date;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 基础实体类, 提供实体通用字段
 */
@Data
public class BaseEntity {
    /**
     * 主键id
     */
    protected Long id;

    /**
     * 创建时间
     */
    protected Date createdOn;

    /**
     * 更新时间
     */
    protected Date updatedOn;
}
