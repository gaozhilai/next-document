package com.gzl.next.document.util;

import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public static String generateToken(String userId) {
        Date expirationDate = new DateTime().plusDays(expiration).toDate();
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put("user_id", userId);
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return "Bearer "+jwt; //jwt前面一般都会加Bearer
    }

    public static String validateToken(String token) {
        try {
            // parse the token.
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace("Bearer ",""))
                    .getBody();
            Object res = body.get("user_id");
            if (res != null) {
                return (String) res;
            } else {
                throw new SysException(SysCodeEnum.UNAUTHORIZED);
            }
        }catch (Exception e){
            throw new SysException(SysCodeEnum.UNAUTHORIZED);
        }
    }
}
