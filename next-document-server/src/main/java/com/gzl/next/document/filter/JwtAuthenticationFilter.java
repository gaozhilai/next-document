package com.gzl.next.document.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gzl.next.document.config.IgnoreTokenPathConfig;
import com.gzl.next.document.enums.SysCodeEnum;
import com.gzl.next.document.exception.SysException;
import com.gzl.next.document.util.CommonResult;
import com.gzl.next.document.util.JwtUtil;
import com.gzl.next.document.util.ResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GaoZhilai
 * Date: 2019/9/29
 * Time: 14:10
 * Description: No Description
 */
@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final PathMatcher pathMatcher = new AntPathMatcher();

    private IgnoreTokenPathConfig ignoreTokenPathConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("jwt filter is running");
        try {
            if(isProtectedUrl(request)) {
                String token = request.getHeader("token");
                //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
                String loginName = JwtUtil.validateToken(token);
                request.setAttribute("user_id", loginName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("application/json;charset=UTF-8");
            if (e instanceof SysException) {
                CommonResult result = ResultUtil.renderFailure(SysCodeEnum.UNAUTHORIZED, null);
                response.getWriter().println(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
            } else {
                CommonResult result = ResultUtil.renderFailure(e.getMessage());
                response.getWriter().println(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
            }
            return;
        }
        //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
        filterChain.doFilter(request, response);
    }

    private boolean isProtectedUrl(HttpServletRequest request) {
        String currPath = request.getServletPath();
        String[] ignoreTokenPaths = ignoreTokenPathConfig.getIgnoreTokenPaths();
        String[] needTokenPaths = ignoreTokenPathConfig.getNeedTokenPaths();
        // 无需授权的路径
        for (String ignoreTokenPath : ignoreTokenPaths) {
            if (pathMatcher.match(ignoreTokenPath, currPath)) {
                return false;
            }
        }
        // 需要授权的路径
        for (String needTokenPath : needTokenPaths) {
            if (pathMatcher.match(needTokenPath, currPath)) {
                return true;
            }
        }
        // 默认需要授权的路径
        return pathMatcher.match("/api/**", currPath);
    }
}