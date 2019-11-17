package com.gzl.next.document.pojo.entity;

import com.gzl.next.document.pojo.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 文档文件夹实体
 */
@Data
public class DocCategory extends BaseEntity {
    /**
     * 文档分类名/文件夹名
     */
    private String categoryName;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 创建者id
     */
    private Long createdBy;
    /**
     * 最近一次更新者id
     */
    private Long updatedBy;
}
