package com.gzl.next.document.mapper;

import com.gzl.next.document.pojo.entity.AccountPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Mapper
public interface AccountPermissionMapper {
    /**
     * 获取用户有效的权限
     * @param roleIds 用户拥有的角色id
     * @param userId 用户id
     * @return
     */
    List<AccountPermission> getAvailablePermission(@Param("roleIds") List<Long> roleIds,
                                                   @Param("userId") Long userId);
}
