package com.gzl.next.document.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author GaoZhilai
 * @date 20/1/28 17:01
 * No Description
 */
@Data
public class DocumentUpdateFORM {
    /**
     * 文档名称
     */
    @NotBlank
    String docName;
    /**
     * 文档内容
     */
    String docContent;
}
