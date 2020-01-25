package com.gzl.next.document.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author GaoZhilai
 * @date 19/12/7 20:17
 * No Description
 */
@Data
public class ProjectFORM {
    @NotBlank
    private String projectName;
    @NotBlank
    private String description;
    @NotNull
    private Boolean privateProject;
}
