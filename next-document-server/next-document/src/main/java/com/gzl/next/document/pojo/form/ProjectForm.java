package com.gzl.next.document.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author GaoZhilai
 * @date 19/12/7 20:17
 * No Description
 */
@Data
public class ProjectForm {
    @NotBlank
    private String projectName;
    @NotBlank
    private String description;
}
