package com.gzl.next.document.service.impl;

import com.gzl.next.document.mapper.AccountUserProjectMapper;
import com.gzl.next.document.pojo.entity.AccountUserProject;
import com.gzl.next.document.pojo.vo.AccountUserProjectVO;
import com.gzl.next.document.service.AccountUserProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GaoZhilai
 * @date 20/1/20 10:30
 * No Description
 */
@Service
public class AccountUserProjectServiceImpl implements AccountUserProjectService {
    @Autowired
    private AccountUserProjectMapper accountUserProjectMapper;

    @Override
    public List<AccountUserProjectVO> getUserProjectByUserId(Long userId) {
        List<AccountUserProjectVO> accountUserProjectVOS = accountUserProjectMapper.getUserProjectByUserId(userId);
        return accountUserProjectVOS;
    }

    @Override
    public int addProjectShortcut(Long userId, Long projectId, Integer shortcutIndex) {
        return accountUserProjectMapper.addProjectShortcut(userId, projectId, shortcutIndex);
    }

    @Override
    public int deleteProjectShortcut(Long userId, Integer shortcutIndex) {
        return accountUserProjectMapper.deleteProjectShortcut(userId, shortcutIndex);
    }
}
