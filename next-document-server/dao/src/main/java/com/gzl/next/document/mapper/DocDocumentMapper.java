package com.gzl.next.document.mapper;


import com.gzl.next.document.pojo.entity.DocDocument;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Repository
public interface DocDocumentMapper {
    /**
     * 获取文档列表
     * @param projectId 文档所属项目id
     * @param categoryId 文档所属文件夹id
     * @return 文档列表
     */
    List<DocDocument> getDocumentListByCategoryId(@Param("projectId") Long projectId,
                                                  @Param("categoryId") Long categoryId);
}
