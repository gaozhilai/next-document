package com.gzl.next.document.controller;

import com.gzl.next.document.pojo.form.ProjectFORM;
import com.gzl.next.document.pojo.form.ShortcutFORM;
import com.gzl.next.document.pojo.form.UpdateProjectFORM;
import com.gzl.next.document.pojo.vo.AccountUserProjectVO;
import com.gzl.next.document.pojo.vo.ProjectDetailVO;
import com.gzl.next.document.pojo.vo.ProjectVO;
import com.gzl.next.document.service.AccountUserProjectService;
import com.gzl.next.document.service.ProjectService;
import com.gzl.next.document.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Autowired
    private AccountUserProjectService accountUserProjectService;

    @GetMapping("/projects")
    public ResponseEntity<CommonResult<PageData<ProjectVO>>> getProjectList(@Min(1) @RequestParam("page") Integer page,
                                                                            @Max(500) @RequestParam("size") Integer size,
                                                                            @RequestParam(value = "id", required = false) Long id,
                                                                            @RequestParam(value = "project_name", required = false) String projectName,
                                                                            @RequestParam(value = "description", required = false) String description) {
        PageData<ProjectVO> projectList = projectService.getProjectList(page, size, id, projectName, description);
        return ResultUtil.renderSuccess(projectList);
    }

    @PostMapping("/project")
    public ResponseEntity<CommonResult> createProject(HttpServletRequest request, @Validated @RequestBody ProjectFORM projectFORM) {
        Long currentUserId = RequestAttributeUtil.getCurrentUserId(request);
        int result = projectService.createProject(projectFORM, currentUserId, currentUserId);
        if (result > 0) {
            return ResultUtil.renderSuccess("创建项目成功");
        } else {
            return ResultUtil.renderFailure("创建项目失败");
        }
    }

    @GetMapping("/project")
    public ResponseEntity<CommonResult<ProjectDetailVO>> getProjectById(@NotNull @RequestParam("project_id") Long projectId) {
        ProjectDetailVO projectById = projectService.getProjectById(projectId);
        return ResultUtil.renderSuccess(projectById);
    }

    @PutMapping("/project")
    public ResponseEntity<CommonResult> updateProjectById(HttpServletRequest request,
                                                          @Validated @RequestBody UpdateProjectFORM projectForm) {
        if (StringUtils.isBlank(projectForm.getProjectName()) && StringUtils.isBlank(projectForm.getDescription())) {
            return ResultUtil.renderFailure("要更新的项目信息不能都为空");
        }
        Long currentUserId = RequestAttributeUtil.getCurrentUserId(request);
        int res = projectService.updateProjectById(projectForm, currentUserId);
        if (res > 0) {
            return ResultUtil.renderSuccess("更新项目信息成功");
        } else {
            return ResultUtil.renderSuccess("更新项目信息失败");
        }
    }

    @GetMapping("/project_shortcut")
    public ResponseEntity<CommonResult<List<AccountUserProjectVO>>> projectShortcut(HttpServletRequest request) {
        Long currentUserId = RequestAttributeUtil.getCurrentUserId(request);
        List<AccountUserProjectVO> userProjectByUserId = accountUserProjectService.getUserProjectByUserId(currentUserId);
        return ResultUtil.renderSuccess(userProjectByUserId);
    }

    @PostMapping("/project_shortcut")
    public ResponseEntity<CommonResult> addProjectShortcut(HttpServletRequest request,
                                                           @Validated @RequestBody ShortcutFORM shortcutFORM) {
        Long currentUserId = RequestAttributeUtil.getCurrentUserId(request);
        accountUserProjectService.addProjectShortcut(currentUserId,
                shortcutFORM.getProjectId(),
                shortcutFORM.getShortcutIndex());
        return ResultUtil.renderSuccess("添加快捷方式成功");
    }

    @DeleteMapping("/project_shortcut/{shortcut_index}")
    public ResponseEntity<CommonResult> deleteProjectShortcut(HttpServletRequest request,
                                                              @NotNull @PathVariable("shortcut_index") Integer shortcutIndex) {
        Long currentUserId = RequestAttributeUtil.getCurrentUserId(request);
        accountUserProjectService.deleteProjectShortcut(currentUserId, shortcutIndex);
        return ResultUtil.renderSuccess("删除快捷方式成功");
    }
}
