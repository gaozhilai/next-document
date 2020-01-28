package com.gzl.next.document.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @author GaoZhilai
 * @date 20/1/26 15:55
 * No Description
 */
@Data
public class DocumentDetailVO {
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

    /**
     * 文档名称
     */
    private String docName;

    /**
     * 文档内容
     */
    private String docContent;

    /**
     * 作者昵称
     */
    private String authorName;
    /**
     * 最近更新者id
     * JsonIgnore注解防止暴露更新者id
     */
    @JsonIgnore
    private Long modifyBy;
    /**
     * 文档更新者昵称
     */
    private String modifyByName;
}
