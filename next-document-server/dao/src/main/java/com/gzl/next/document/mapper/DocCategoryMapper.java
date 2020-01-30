package com.gzl.next.document.mapper;


import com.gzl.next.document.pojo.entity.DocCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Repository
public interface DocCategoryMapper {
    /**
     * 获取目录列表
     * @param projectId 项目id
     * @param parentId 父级目录id, 0为根目录级别目录
     * @return 符合条件的目录
     */
    List<DocCategory> getCategoryByProjectId(@Param("projectId") Long projectId,
                                             @Param("parentId") Long parentId);

    /**
     * 更新文件夹名称
     * @param categoryId 文件夹id
     * @param name 新名称
     * @param updatedBy 更新者id
     * @return 大于1成功
     */
    int updateCategory(@Param("categoryId") Long categoryId,
                       @Param("name") String name,
                       @Param("updatedBy") Long updatedBy);
}
