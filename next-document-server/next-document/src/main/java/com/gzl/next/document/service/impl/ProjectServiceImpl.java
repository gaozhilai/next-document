package com.gzl.next.document.service.impl;

import com.github.pagehelper.PageHelper;
import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import com.gzl.next.document.mapper.DocProjectMapper;
import com.gzl.next.document.pojo.entity.DocProject;
import com.gzl.next.document.pojo.form.ProjectForm;
import com.gzl.next.document.pojo.vo.ProjectDetailVO;
import com.gzl.next.document.pojo.vo.ProjectVO;
import com.gzl.next.document.service.ProjectService;
import com.gzl.next.document.util.PageData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/12/7 19:04
 * No Description
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private DocProjectMapper projectMapper;

    @Override
    public PageData<ProjectVO> getProjectList(Integer page, Integer size, Long id, String projectName, String description) {
        PageHelper.startPage(page, size);
        List<DocProject> projectList = projectMapper.getProjectList(id, projectName, description);
        List<ProjectVO> projectVOS = new ArrayList<>();
        for (DocProject docProject : projectList) {
            ProjectVO temp = new ProjectVO();
            BeanUtils.copyProperties(docProject, temp);
            projectVOS.add(temp);
        }
        PageData<ProjectVO> docProjectPageData = PageData.converPageData(projectVOS);
        return docProjectPageData;
    }

    @Override
    public int createProject(ProjectForm projectForm, Long createdBy, Long updatedBy) {
        int result = projectMapper.createProject(projectForm.getProjectName(),
                projectForm.getDescription(), createdBy, updatedBy);
        return result;
    }

    @Override
    public ProjectDetailVO getProjectById(Long id) {
        DocProject project = projectMapper.getProjectById(id);
        ProjectDetailVO projectDetailVO = new ProjectDetailVO();
        BeanUtils.copyProperties(project, projectDetailVO);
        return projectDetailVO;
    }
}
