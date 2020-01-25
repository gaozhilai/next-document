package com.gzl.next.document.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 20/1/24 12:45
 * No Description
 */
@Data
public class CategoryListVO {
    private List<CategoryVO> categories;
    private List<DocumentVO> documents;
}
