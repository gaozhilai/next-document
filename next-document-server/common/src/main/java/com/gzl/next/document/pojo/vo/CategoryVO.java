package com.gzl.next.document.pojo.vo;

import lombok.Data;

/**
 * @author GaoZhilai
 * @date 20/1/24 11:32
 * No Description
 */
@Data
public class CategoryVO {
    /**
     * 主键id
     */
    protected Long id;
    /**
     * 文档分类名/文件夹名
     */
    private String categoryName;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 父级目录id
     */
    private Long parentId;
}
