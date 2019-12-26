package com.gzl.next.document.util;

import javax.servlet.ServletRequest;

/**
 * @author GaoZhilai
 * @date 2019/12/26 9:36
 * No Description
 */
public class RequestAttributeUtil {
    public static Long getCurrentUserId(ServletRequest request) {
        return (Long) request.getAttribute("userId");
    }
}
