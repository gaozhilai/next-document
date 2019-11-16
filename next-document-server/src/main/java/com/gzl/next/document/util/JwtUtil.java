package com.gzl.next.document.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Author: GaoZhilai
 * Date: 2019/9/29
 * Time: 14:15
 * Description: No Description
 */
@Slf4j
@Component
public class JwtUtil {
    static final String SECRET = "next_document";
    private static int expiration;

    @Value("${token.expiration.days}")
    public void setExpiration(int expiration) {
        JwtUtil.expiration = expiration;
    }

    public static String generateToken(String loginName) {
        Date expirationDate = new DateTime().plusDays(expiration).toDate();
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        String token = JWT.create()
                .withClaim("login_name", loginName)
                //到期时间
                .withExpiresAt(expirationDate)
                //创建一个新的JWT，并使用给定的算法进行标记
                .sign(algorithm);
        return token;
    }

    public static String validateToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            String loginName = jwt.getClaim("login_name").asString();
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            if (loginName != null) {
                return loginName;
            } else {
                throw new SysException(SysCodeEnum.UNAUTHORIZED);
            }
        }catch (Exception e){
            throw new SysException(SysCodeEnum.UNAUTHORIZED);
        }
    }

    public static String getClaim(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String loginName = jwt.getClaim("login_name").asString();
        return loginName;
    }
}
