package com.gzl.next.document.service;

import com.gzl.next.document.pojo.entity.DocCategory;
import com.gzl.next.document.pojo.vo.CategoryListVO;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 20/1/23 18:13
 * No Description
 */
public interface CategoryService {
    /**
     * 获取目录列表
     * @param projectId 项目id
     * @param parentId 父级目录id, 0为根目录级别目录
     * @return 符合条件的目录
     */
    CategoryListVO getCategoryByProjectId(Long projectId, Long parentId);
}
