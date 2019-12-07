package com.gzl.next.document.pojo.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author GaoZhilai
 * @date 19/12/7 16:28
 * No Description
 */
@Data
public class ProjectQUERY {
    private Long id;
    private String projectName;
    private String description;
    @NotNull
    private Integer page;
    @NotNull
    private Integer size;
}
