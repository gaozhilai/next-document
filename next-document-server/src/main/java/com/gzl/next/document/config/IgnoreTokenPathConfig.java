package com.gzl.next.document.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Author: GaoZhilai
 * Date: 2019/9/29
 * Time: 14:14
 * Description: No Description
 */
@Data
@Component
@ConfigurationProperties(prefix = "token")
public class IgnoreTokenPathConfig {
    private String[] ignoreTokenPaths;

    private String[] needTokenPaths;
}