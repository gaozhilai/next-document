package com.gzl.next.document.mapper;

import com.gzl.next.document.pojo.entity.AccountUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Repository
public interface AccountUserMapper {
    /**
     * 根据登录名获取用户
     * @param loginName 登录名
     * @return 用户
     */
    AccountUser getAccountUserByLoginName(@Param("loginName") String loginName);

    /**
     * 新增用户
     * @param loginName 登录名
     * @param nickname 昵称
     * @param password 密码
     * @param salt 盐值
     * @param valid 1有效, 0无效
     * @param avatar 头像url
     * @param phone 电话
     * @param email 邮箱
     * @return 用户
     */
    int addUser(@Param("loginName") String loginName, @Param("nickname") String nickname,
                @Param("password") String password, @Param("salt") String salt, @Param("valid") Integer valid,
                @Param("avatar") String avatar, @Param("phone") String phone, @Param("email") String email,
                @Param("gender") Short gender);

    /**
     * 批量插入用户
     * @param users 用户列表
     * @return 成功插入数据条数
     */
    int insertBatchUser(@Param("users") List<AccountUser> users);

    /**
     * 根据用户id获取用户对象
     * @param userId 用户id
     * @return 用户
     */
    AccountUser getUserById(@Param("userId") Long userId);
}
