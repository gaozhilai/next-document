package com.gzl.next.document.pojo.entity;

import com.gzl.next.document.pojo.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: 文档实体
 */
@Data
public class DocDocument extends BaseEntity {
    /**
     * 所属分类id
     */
    private Long categoryId;
    /**
     * 所属项目id
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
     * 更新id
     */
    private Long modifyId;
    /**
     * 是否共享编辑
     */
    private Boolean freeEditing;
    /**
     * 共享Url标识
     */
    private String shareUrl;
    /**
     * 文档是否可用
     */
    private Boolean validate;
    /**
     * 文档内容
     */
    private String docContent;
}
