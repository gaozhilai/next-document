package com.gzl.next.document.filter;

import com.gzl.next.document.exception.SysException;
import com.gzl.next.document.pojo.entity.AccountUser;
import com.gzl.next.document.util.JwtUtil;
import com.gzl.next.document.util.ResultUtil;
import com.gzl.next.document.util.UserCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: GaoZhilai
 * Date: 2019/9/29
 * Time: 14:10
 * Description: No Description
 */
@Slf4j
public class CrosFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("cros filter is running");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "1800");
        response.setHeader("Access-Control-Allow-Headers", "content-type,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        if(request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return ;
        }
        String token = request.getHeader("token");
        if (!StringUtils.isBlank(token)) {
            String loginName = JwtUtil.getClaim(token);
            AccountUser user = UserCache.userCache.getUnchecked(loginName);
            if (user != null) {
                request.setAttribute("userId", user.getId());
            }
        }
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getCause() instanceof SysException) {
                ResultUtil.renderMsgWidthPrimitiveWay(response, ((SysException) e.getCause()).getSysCodeEnum());
            }
        }
    }
}
