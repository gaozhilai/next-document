package com.gzl.next.document.service;

import com.gzl.next.document.pojo.entity.DocDocument;
import com.gzl.next.document.pojo.form.DocumentUpdateFORM;
import com.gzl.next.document.pojo.vo.DocumentDetailVO;

/**
 * @author GaoZhilai
 * @date 20/1/26 15:53
 * No Description
 */
public interface DocumentService {
    /**
     * 查询文档详情
     * @param documentId 文档id
     * @return 文档
     */
    DocumentDetailVO getDocumentDetailById(Long documentId);

    /**
     * 更新文档基本信息
     * @param document 文档基本信息
     * @param documentId
     * @param modifyBy 更新者id
     * @return 大于1成功
     */
    int updateDocument(DocumentUpdateFORM document, Long documentId, Long modifyBy);
}
