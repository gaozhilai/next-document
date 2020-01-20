package com.gzl.next.document.mapper;

import com.gzl.next.document.pojo.entity.AccountUserProject;
import com.gzl.next.document.pojo.vo.AccountUserProjectVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 20/1/19 21:20
 * No Description
 */
@Repository
public interface AccountUserProjectMapper {
    /**
     * 获取用户项目列表
     * @param userId 用户id
     * @return 项目列表
     */
    List<AccountUserProjectVO> getUserProjectByUserId(@Param("userId") Long userId);

    /**
     * 添加项目快捷方式
     * @param userId 用户id
     * @param projectId 项目id
     * @param shortcutIndex 快捷方式下标
     * @return 1成功
     */
    int addProjectShortcut(@Param("userId") Long userId,
                           @Param("projectId") Long projectId,
                           @Param("shortcutIndex") Integer shortcutIndex);

    /**
     * 删除项目快捷方式
     * @param userId 用户id
     * @param shortcutIndex 快捷方式下标
     * @return 1成功
     */
    int deleteProjectShortcut(@Param("userId") Long userId,
                              @Param("shortcutIndex") Integer shortcutIndex);
}
