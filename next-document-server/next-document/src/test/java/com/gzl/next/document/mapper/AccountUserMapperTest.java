package com.gzl.next.document.mapper;

import com.gzl.next.document.base.BaseTest;
import com.gzl.next.document.pojo.entity.AccountUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@Slf4j
public class AccountUserMapperTest extends BaseTest {
    @Autowired
    private AccountUserMapper accountUserMapper;

    @Test
    public void testGetUserByLoginName() {
        AccountUser user = accountUserMapper.getAccountUserByLoginName("gzl");
        log.info("获取到用户, {}", user);
    }
}
