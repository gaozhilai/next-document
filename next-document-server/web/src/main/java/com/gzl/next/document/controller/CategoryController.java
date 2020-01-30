package com.gzl.next.document.controller;

import com.gzl.next.document.pojo.form.CategoryFORM;
import com.gzl.next.document.pojo.vo.CategoryListVO;
import com.gzl.next.document.service.CategoryService;
import com.gzl.next.document.util.CommonResult;
import com.gzl.next.document.util.RequestAttributeUtil;
import com.gzl.next.document.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * @author GaoZhilai
 * @date 20/1/30 15:09
 * No Description
 */
@RestController
@Validated
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/categories/{project_id}/{parent_id}")
    public ResponseEntity<CommonResult<CategoryListVO>> getCategoryList(@PathVariable("project_id") Long projectId,
                                                                        @PathVariable("parent_id") Long parentId) {
        CategoryListVO categoryListVO = categoryService.getCategoryByProjectId(projectId, parentId);
        return ResultUtil.renderSuccess(categoryListVO);
    }

    @PatchMapping("/category/{category_id}")
    public ResponseEntity<CommonResult> updateCategory(HttpServletRequest request,
                                                       @NotNull @PathVariable("category_id") Long categoryId,
                                                       @Validated @RequestBody CategoryFORM categoryFORM) {
        Long currentUserId = RequestAttributeUtil.getCurrentUserId(request);
        categoryService.updateCategory(categoryId, categoryFORM.getCategoryName(), currentUserId);
        return ResultUtil.renderSuccess("重命名文件夹成功");
    }
}
