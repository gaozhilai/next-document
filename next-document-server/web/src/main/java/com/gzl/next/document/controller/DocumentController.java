package com.gzl.next.document.controller;

import com.gzl.next.document.pojo.entity.DocCategory;
import com.gzl.next.document.pojo.form.DocumentUpdateFORM;
import com.gzl.next.document.pojo.vo.CategoryListVO;
import com.gzl.next.document.pojo.vo.DocumentDetailVO;
import com.gzl.next.document.service.CategoryService;
import com.gzl.next.document.service.DocumentService;
import com.gzl.next.document.util.CommonResult;
import com.gzl.next.document.util.RequestAttributeUtil;
import com.gzl.next.document.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author GaoZhilai
 * @date 20/1/24 11:04
 * No Description
 */
@RestController
@RequestMapping("/document")
@Validated
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/detail/{document_id}")
    public ResponseEntity<CommonResult<DocumentDetailVO>> getDocumentDetailById(@PathVariable("document_id") Long documentId) {
        DocumentDetailVO documentDetailById = documentService.getDocumentDetailById(documentId);
        return ResultUtil.renderSuccess(documentDetailById);
    }

    @PatchMapping("/{document_id}")
    public ResponseEntity<CommonResult> updateDocument(HttpServletRequest request,
                                                       @NotNull @PathVariable("document_id") Long documentId,
                                                       @Validated @RequestBody DocumentUpdateFORM document) {
        Long currentUserId = RequestAttributeUtil.getCurrentUserId(request);
        documentService.updateDocument(document, documentId, currentUserId);
        return ResultUtil.renderSuccess("更新文档成功");
    }
}
