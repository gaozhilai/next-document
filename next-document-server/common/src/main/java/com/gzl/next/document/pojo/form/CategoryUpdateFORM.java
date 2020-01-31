package com.gzl.next.document.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author GaoZhilai
 * @date 20/1/30 15:14
 * No Description
 */
@Data
public class CategoryUpdateFORM {
    @NotBlank
    private String categoryName;
}
