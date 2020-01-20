package com.gzl.next.document.service;

import com.gzl.next.document.pojo.entity.AccountUserProject;
import com.gzl.next.document.pojo.vo.AccountUserProjectVO;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 20/1/20 10:05
 * No Description
 */
public interface AccountUserProjectService {
    /**
     * 获取用户项目列表
     * @param userId 用户id
     * @return 项目列表
     */
    List<AccountUserProjectVO> getUserProjectByUserId(Long userId);

    /**
     * 添加项目快捷方式
     * @param userId 用户id
     * @param projectId 项目id
     * @param shortcutIndex 快捷方式下标
     * @return 1成功
     */
    int addProjectShortcut(Long userId, Long projectId, Integer shortcutIndex);

    /**
     * 删除项目快捷方式
     * @param userId 用户id
     * @param shortcutIndex 快捷方式下标
     * @return 1成功
     */
    int deleteProjectShortcut(Long userId, Integer shortcutIndex);
}
