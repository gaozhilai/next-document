package com.gzl.next.document.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author GaoZhilai
 * @date 20/1/20 14:35
 * No Description
 */
@Data
public class ShortcutFORM {
    /**
     * 项目id
     */
    @NotNull
    private Long projectId;
    /**
     * 快捷方式下标
     */
    @NotNull
    private Integer shortcutIndex;
}
