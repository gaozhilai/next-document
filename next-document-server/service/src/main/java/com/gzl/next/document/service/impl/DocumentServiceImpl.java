package com.gzl.next.document.service.impl;

import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import com.gzl.next.document.mapper.AccountUserMapper;
import com.gzl.next.document.mapper.DocDocumentMapper;
import com.gzl.next.document.pojo.entity.AccountUser;
import com.gzl.next.document.pojo.entity.DocDocument;
import com.gzl.next.document.pojo.form.DocumentUpdateFORM;
import com.gzl.next.document.pojo.vo.DocumentDetailVO;
import com.gzl.next.document.service.DocumentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GaoZhilai
 * @date 20/1/26 15:54
 * No Description
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocDocumentMapper docDocumentMapper;
    @Autowired
    private AccountUserMapper accountUserMapper;

    @Override
    public DocumentDetailVO getDocumentDetailById(Long documentId) {
        DocumentDetailVO documentDetailById = docDocumentMapper.getDocumentDetailById(documentId);
        AccountUser userById = accountUserMapper.getUserById(documentDetailById.getModifyBy());
        documentDetailById.setModifyByName(userById.getNickname());
        return documentDetailById;
    }

    @Override
    public int updateDocument(DocumentUpdateFORM document, Long documentId, Long modifyBy) {
        DocDocument documentById = docDocumentMapper.getDocumentById(documentId);
        Long createdBy = documentById.getCreatedBy();
        if (!documentById.getFreeEditing() && !createdBy.equals(modifyBy)) {
            throw new SysException(SysCodeEnum.ONLY_CREATOR_CAN_UPDATE);
        }
        return docDocumentMapper.updateDocument(document.getDocName(), document.getDocContent(), modifyBy,
                documentId);
    }
}
