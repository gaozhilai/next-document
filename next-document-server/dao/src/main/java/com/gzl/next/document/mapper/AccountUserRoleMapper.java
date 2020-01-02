package com.gzl.next.document.mapper;


import com.gzl.next.document.pojo.entity.AccountUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Repository
public interface AccountUserRoleMapper {
    /**
     * 批量插入用户角色关系
     * @param userRoles 用户角色关系
     * @return 返回插入数据条数
     */
    int insertBatchUserRole(@Param("userRoles")List<AccountUserRole> userRoles);
}
