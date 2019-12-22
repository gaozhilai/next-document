package com.gzl.next.document.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author GaoZhilai
 * @date 19/12/22 11:25
 * 更新项目信息
 */
@Data
public class UpdateProjectForm {
    @NotNull
    private Long id;
    private String projectName;
    private String description;
}
