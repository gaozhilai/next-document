package com.gzl.next.document.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gzl.next.document.base.BaseTest;
import com.gzl.next.document.util.JwtUtil;
import org.junit.Test;

/**
 * @author GaoZhilai
 * @date 19/11/16
 */
public class JwtTest extends BaseTest {
    @Test
    public void testGenerateToken() {
        String token = JwtUtil.generateToken("gzl");
        log.info("获取到Token为, {}", token);
        DecodedJWT jwt = JWT.decode(token);
        String loginName = jwt.getClaim("login_name").asString();
        log.info("没有秘钥获得token中信息为, {}", loginName);
        String res = JwtUtil.validateToken(token);
        log.info("token验证通过, {}", res);
    }
}
