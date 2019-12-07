package com.gzl.next.document.shiro.token;

import lombok.AllArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

import javax.servlet.ServletRequest;

/**
 * @author GaoZhilai
 * @date 19/11/16
 */
@AllArgsConstructor
public class JwtToken implements AuthenticationToken {
    private String token;
    private ServletRequest request;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public ServletRequest getRequest() {
        return request;
    }
}
