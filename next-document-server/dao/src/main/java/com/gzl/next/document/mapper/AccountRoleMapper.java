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

    /**
     * 批量插入角色
     * @param roles 角色列表
     * @return 插入数据条数
     */
    int insertBatchRole(@Param("roles") List<AccountRole> roles);

    /**
     * 根据roleCode获得角色
     * @param roleCode 角色代码
     * @return 角色
     */
    AccountRole getRoleByRoleCode(@Param("roleCode") String roleCode);
}
