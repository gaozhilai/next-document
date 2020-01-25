package com.gzl.next.document.controller;

import com.gzl.next.document.pojo.entity.DocCategory;
import com.gzl.next.document.pojo.vo.CategoryListVO;
import com.gzl.next.document.service.CategoryService;
import com.gzl.next.document.util.CommonResult;
import com.gzl.next.document.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 20/1/24 11:04
 * No Description
 */
@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories/{project_id}/{parent_id}")
    public ResponseEntity<CommonResult<CategoryListVO>> getCategoryList(@PathVariable("project_id") Long projectId,
                                                                 @PathVariable("parent_id") Long parentId) {
        CategoryListVO categoryListVO = categoryService.getCategoryByProjectId(projectId, parentId);
        return ResultUtil.renderSuccess(categoryListVO);
    }
}
