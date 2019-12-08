package com.gzl.next.document.pojo.vo;

import lombok.Data;

/**
 * @author GaoZhilai
 * @date 19/12/8 14:21
 * No Description
 */
@Data
public class ProjectVO {
    /**
     * 主键id
     */
    protected Long id;
    /**
     * 项目名字
     */
    private String projectName;
    /**
     * 项目描述
     */
    private String description;
}
