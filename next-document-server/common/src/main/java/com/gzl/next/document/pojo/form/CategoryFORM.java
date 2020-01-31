package com.gzl.next.document.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author GaoZhilai
 * @date 20/1/30 22:56
 * No Description
 */
@Data
public class CategoryFORM {
    /**
     * 项目id
     */
    @NotNull
    private Long projectId;
    /**
     * 文件夹父id, 0根目录
     */
    @NotNull
    private Long parentId;
    /**
     * 文件夹名称
     */
    @NotNull
    private String categoryName;
}
