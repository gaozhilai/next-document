package com.gzl.next.document.config;

import com.gzl.next.document.filter.CrosFilter;
import com.gzl.next.document.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: GaoZhilai
 * Date: 2019/9/29
 * Time: 14:53
 * Description: No Description
 */
@Configuration
public class FilterConfig {
    @Bean
    @Autowired
    public FilterRegistrationBean jwtFilter(IgnoreTokenPathConfig ignoreTokenPathConfig) {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(ignoreTokenPathConfig);
        registrationBean.setFilter(filter);
        registrationBean.setOrder(6);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean crosFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CrosFilter filter = new CrosFilter();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(5);
        return registrationBean;
    }
}