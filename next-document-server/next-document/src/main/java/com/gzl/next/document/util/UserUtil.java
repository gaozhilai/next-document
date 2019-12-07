package com.gzl.next.document.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: GaoZhilai
 * Date: 2019/9/29
 * Time: 14:52
 * Description: No Description
 */
public class UserUtil {
    public static String getCurrentUserId(HttpServletRequest request) {
        return (String) request.getAttribute("user_id");
    }
}

