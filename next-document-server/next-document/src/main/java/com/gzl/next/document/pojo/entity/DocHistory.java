package com.gzl.next.document.pojo.entity;

import com.gzl.next.document.pojo.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 文档历史实体
 */
@Data
public class DocHistory extends BaseEntity {
    /**
     * 文件夹/分类id
     */
    private Long categoryId;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 文档名字
     */
    private String docName;
    /**
     * 创建者id
     */
    private Long createdBy;
    /**
     * 最近一次更新者id
     */
    private Long updatedId;
    /**
     * 是否允许共享编辑
     */
    private Boolean freeEditing;
    /**
     * 共享Url标识
     */
    private String shareUrl;
    /**
     * 文档是否有效
     */
    private Boolean validate;
    /**
     * 文档内容
     */
    private String docContent;
}
