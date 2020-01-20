package com.gzl.next.document.pojo.entity;

import com.gzl.next.document.pojo.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author GaoZhilai
 * @date 20/1/19 19:40
 * 用户项目面板快捷方式
 */
@Data
public class AccountUserProject extends BaseEntity {
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
}
