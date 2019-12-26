package com.gzl.next.document.mapper;

import com.gzl.next.document.pojo.entity.AccountRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Repository
public interface AccountRoleMapper {
    /**
     * 通过用户id获取角色
     * @param userId 用户id
     * @return 拥有的角色
     */
    List<AccountRole> getRoleByUserId(@Param("userId") Long userId);
}
