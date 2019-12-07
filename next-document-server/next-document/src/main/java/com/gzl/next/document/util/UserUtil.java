package com.gzl.next.document.util;

import javax.servlet.ServletRequest;

/**
 * Author: GaoZhilai
 * Date: 2019/9/29
 * Time: 14:52
 * Description: No Description
 */
public class UserUtil {
    public static Long getCurrentUserId(ServletRequest request) {
        return (Long) request.getAttribute("userId");
    }
}

