package com.gzl.next.document.mapper;


import com.gzl.next.document.pojo.entity.DocDocument;
import com.gzl.next.document.pojo.vo.DocumentDetailVO;
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

    /**
     * 通过文档id获取文档对象
     * @param documentId 文档id
     * @return 文档对象
     */
    DocDocument getDocumentById(@Param("documentId") Long documentId);

    /**
     * 通过文档id查询文档详情
     * @param documentId 文档id
     * @return 文档
     */
    DocumentDetailVO getDocumentDetailById(@Param("documentId") Long documentId);

    /**
     * 更新文档基本信息
     * @param docName 文档名称
     * @param docContent 文档内容
     * @param modifyBy 更新者id
     * @param documentId 更新者id
     * @return 大于1成功
     */
    int updateDocument(@Param("docName") String docName, @Param("docContent") String docContent,
                       @Param("modifyBy") Long modifyBy, @Param("documentId") Long documentId);
}
