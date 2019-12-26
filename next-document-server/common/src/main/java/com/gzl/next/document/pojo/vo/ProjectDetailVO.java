package com.gzl.next.document.pojo.vo;

import com.gzl.next.document.pojo.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author GaoZhilai
 * @date 19/12/8 14:34
 * No Description
 */
@Data
public class ProjectDetailVO extends BaseEntity {
    /**
     * 项目名字
     */
    private String projectName;
    /**
     * 项目描述
     */
    private String description;
}
