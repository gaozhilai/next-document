package com.gzl.next.document.mapper;

import com.gzl.next.document.pojo.entity.AccountUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author GaoZhilai
 * @date 19/11/17
 * description: No Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AccountUserMapperTest {
    @Autowired
    private AccountUserMapper accountUserMapper;

    @Test
    public void testGetUserByLoginName() {
        AccountUser user = accountUserMapper.getAccountUserByLoginName("gzl");
        log.info("获取到用户, {}", user);
    }
}
