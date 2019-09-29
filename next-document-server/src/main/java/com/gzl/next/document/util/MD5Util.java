package com.gzl.next.document.util;

import org.springframework.util.DigestUtils;

/**
 * Author: GaoZhilai
 * Date: 2019/9/29
 * Time: 14:52
 * Description: No Description
 */
public class MD5Util {
    public static final String SALT = "next_document";

    public static String md5DigestAsHex(String origin) {
        return DigestUtils.md5DigestAsHex(origin.getBytes());
    }

    public static String getUserPassword(String password) {
        return DigestUtils.md5DigestAsHex((password + SALT).getBytes());
    }
}
