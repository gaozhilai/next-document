package com.gzl.next.document.enums;

/**
 * @author GaoZhilai
 * @date 19/12/22 13:26
 * No Description
 */
public enum CommonStatusEnum {
    /**
     * 无效
     */
    INVALID(0),
    /**
     * 有效
     */
    VALID(1);

    private int code;

    CommonStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
