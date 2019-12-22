package com.gzl.next.document.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

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

    /**
     * 通过盐值和密码获得数据库中存储的加密后的密码
     * @param password
     * @param salt
     * @return
     */
    public static String getRealPwd(String password, String salt) {
        // 加密算法MD5
        // 迭代次数
        String md5Pwd = new SimpleHash("MD5", password,
                ByteSource.Util.bytes(salt), 2).toHex();
        return md5Pwd;
    }
}

