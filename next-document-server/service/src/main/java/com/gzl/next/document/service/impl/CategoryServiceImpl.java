package com.gzl.next.document.service.impl;

import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import com.gzl.next.document.mapper.DocCategoryMapper;
import com.gzl.next.document.mapper.DocDocumentMapper;
import com.gzl.next.document.pojo.entity.DocCategory;
import com.gzl.next.document.pojo.entity.DocDocument;
import com.gzl.next.document.pojo.form.CategoryFORM;
import com.gzl.next.document.pojo.vo.CategoryListVO;
import com.gzl.next.document.pojo.vo.CategoryVO;
import com.gzl.next.document.pojo.vo.DocumentVO;
import com.gzl.next.document.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GaoZhilai
 * @date 20/1/23 18:15
 * No Description
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private DocCategoryMapper docCategoryMapper;
    @Autowired
    private DocDocumentMapper docDocumentMapper;

    @Override
    public CategoryListVO getCategoryByProjectId(Long projectId, Long parentId) {
        CategoryListVO categoryListVO = new CategoryListVO();
        List<DocCategory> categoryByProjectId = docCategoryMapper.getCategoryByProjectId(projectId, parentId);
        List<CategoryVO> categoryVOS = new ArrayList<>();
        categoryByProjectId.stream().forEach(ele -> {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(ele, categoryVO);
            categoryVOS.add(categoryVO);
        });
        categoryListVO.setCategories(categoryVOS);
        List<DocDocument> documentListByCategoryId = docDocumentMapper.getDocumentListByCategoryId(projectId, parentId);
        List<DocumentVO> documentVOS = new ArrayList<>();
        documentListByCategoryId.stream().forEach(ele -> {
            DocumentVO documentVO = new DocumentVO();
            BeanUtils.copyProperties(ele, documentVO);
            documentVOS.add(documentVO);
        });
        categoryListVO.setDocuments(documentVOS);
        return categoryListVO;
    }

    @Override
    public int updateCategory(Long categoryId, String name, Long updatedBy) {
        return docCategoryMapper.updateCategory(categoryId, name, updatedBy);
    }

    @Override
    public int createCategory(CategoryFORM categoryFORM, Long createdBy) {
        int sameNameFolderCount = docCategoryMapper.getSameNameFolderCount(categoryFORM.getProjectId(),
                categoryFORM.getParentId(), categoryFORM.getCategoryName());
        if (sameNameFolderCount > 0) {
            throw new SysException(SysCodeEnum.FOLDER_ALREADY_EXIST);
        }
        return docCategoryMapper.createCategory(categoryFORM.getProjectId(),
                categoryFORM.getParentId(), createdBy, categoryFORM.getCategoryName());
    }
}
