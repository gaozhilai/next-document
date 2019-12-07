package com.gzl.next.document.service.impl;

import com.github.pagehelper.PageHelper;
import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import com.gzl.next.document.mapper.DocProjectMapper;
import com.gzl.next.document.pojo.entity.DocProject;
import com.gzl.next.document.pojo.form.ProjectForm;
import com.gzl.next.document.pojo.query.ProjectQUERY;
import com.gzl.next.document.service.ProjectService;
import com.gzl.next.document.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PageData<DocProject> getProjectList(Integer page, Integer size, Long id, String projectName, String description) {
        PageHelper.startPage(page, size);
        List<DocProject> projectList = projectMapper.getProjectList(id, projectName, description);
        PageData<DocProject> docProjectPageData = PageData.converPageData(projectList);
        return docProjectPageData;
    }

    @Override
    public int createProject(ProjectForm projectForm, Long createdBy, Long updatedBy) {
        int result = projectMapper.createProject(projectForm.getProjectName(),
                projectForm.getDescription(), createdBy, updatedBy);
        return result;
    }
}
