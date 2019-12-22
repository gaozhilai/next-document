package com.gzl.next.document.service;

import com.gzl.next.document.pojo.entity.DocProject;
import com.gzl.next.document.pojo.form.ProjectForm;
import com.gzl.next.document.pojo.form.UpdateProjectForm;
import com.gzl.next.document.pojo.vo.ProjectDetailVO;
import com.gzl.next.document.pojo.vo.ProjectVO;
import com.gzl.next.document.util.PageData;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/12/7 16:39
 * No Description
 */
public interface ProjectService {
    /**
     * 查询项目列表
     * @param page 页码
     * @param size 尺寸
     * @param id id 精确查询
     * @param projectName 项目名称模糊查询
     * @param description 项目描述模糊查询
     * @return
     */
    PageData<ProjectVO> getProjectList(Integer page, Integer size, Long id, String projectName, String description);

    /**
     * 新建项目
     * @param projectForm 项目信息
     * @param createdBy 创建者id
     * @param updatedBy 更新者id
     * @return 1成功, 0失败
     */
    int createProject(ProjectForm projectForm, Long createdBy, Long updatedBy);

    /**
     * 查询项目详情
     * @param id 项目id
     * @return 项目详情
     */
    ProjectDetailVO getProjectById(Long id);

    /**
     * 更新项目信息
     * @param projectForm 项目信息
     * @param updatedBy 更新者id
     * @return 1成功
     */
    int updateProjectById(UpdateProjectForm projectForm, Long updatedBy);
}
