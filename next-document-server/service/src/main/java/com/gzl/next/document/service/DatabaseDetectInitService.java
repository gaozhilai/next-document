package com.gzl.next.document.service;

/**
 * @author GaoZhilai
 * @date 19/12/26 22:30
 * 检测和初始化数据库表结构以及必要数据
 */
public interface DatabaseDetectInitService {
    /**
     * 检测和初始化数据库表结构
     */
    void detectInitDatabase();

    /**
     * 初始化以必要数据
     */
    void initConfigModelData();
}
