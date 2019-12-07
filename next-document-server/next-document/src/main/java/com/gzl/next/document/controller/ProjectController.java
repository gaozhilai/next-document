package com.gzl.next.document.controller;

import com.gzl.next.document.pojo.entity.DocProject;
import com.gzl.next.document.pojo.form.ProjectForm;
import com.gzl.next.document.pojo.query.ProjectQUERY;
import com.gzl.next.document.service.ProjectService;
import com.gzl.next.document.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author GaoZhilai
 * @date 19/12/7 19:10
 * 项目相关接口
 */
@RestController
@Validated
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public ResponseEntity<CommonResult<PageData<DocProject>>> getProjectList(@Min(1) @RequestParam("page") Integer page,
                                                                             @Max(500) @RequestParam("size") Integer size,
                                                                             @RequestParam(value = "id", required = false) Long id,
                                                                             @RequestParam(value = "project_name", required = false) String projectName,
                                                                             @RequestParam(value = "description", required = false) String description) {
        PageData<DocProject> projectList = projectService.getProjectList(page, size, id, projectName, description);
        return ResultUtil.renderSuccess(projectList);
    }

    @PostMapping("/project")
    public ResponseEntity<CommonResult> createProject(HttpServletRequest request, @Validated @RequestBody ProjectForm projectForm) {
        Long currentUserId = UserUtil.getCurrentUserId(request);
        int result = projectService.createProject(projectForm, currentUserId, currentUserId);
        if (result > 0) {
            return ResultUtil.renderSuccess("创建项目成功");
        } else {
            return ResultUtil.renderFailure("创建项目失败");
        }
    }
}
