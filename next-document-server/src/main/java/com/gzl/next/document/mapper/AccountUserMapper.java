package com.gzl.next.document.mapper;

import com.gzl.next.document.pojo.entity.AccountUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Mapper
public interface AccountUserMapper {
    /**
     * 根据登录名获取用户
     * @param loginName 登录名
     * @return 用户
     */
    AccountUser getAccountUserByLoginName(@Param("loginName") String loginName);
}
