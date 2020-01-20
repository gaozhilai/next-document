package com.gzl.next.document.pojo.vo;

import lombok.Data;

/**
 * @author GaoZhilai
 * @date 20/1/20 10:27
 * No Description
 */
@Data
public class AccountUserProjectVO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 项目id
     */
    private Long projectId;
    /**
     * 快捷方式下标
     */
    private Integer shortcutIndex;
    /**
     * 项目名称
     */
    private String projectName;
}
