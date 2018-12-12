package com.bill.materiel.consts;

/**
 * @author Bill
 * @date 2018/11/13 0013
 */
public enum WeChatConsts {
    APPID("wxeb7481fed0fe22c6"), APPSECRET("511dfafa1decf179283f6398e3774749"), TOKEN("weixin");

    private final String value;

    WeChatConsts(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
