package com.gzl.next.document.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gzl.next.document.util.JwtUtil;
import com.gzl.next.document.util.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author GaoZhilai
 * @date 19/11/16
 */
@Slf4j
public class JwtTest {
    @Test
    public void testGenerateToken() {
        String token = JwtUtil.generateToken("gzl", "salt1024", "password");
        log.info("获取到Token为, {}", token);
        DecodedJWT jwt = JWT.decode(token);
        String loginName = jwt.getClaim("login_name").asString();
        log.info("没有秘钥获得token中信息为, {}", loginName);
        String res = JwtUtil.validateToken(token, "next_document", "password");
        log.info("token验证通过, {}", res);
    }

    @Test
    public void testGeneratePassword() {
        String password = EncryptionUtil.getRealPwd("123", "salt1024");
        log.info("password {}", password);
    }
}
