package com.ikamobile.pa.common.enums;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/13.
 */
public enum VerityCodeEnum {

    CREATE_ORDER("下单验证码"),REGISTER("注册验证码");

    private String desc;

    VerityCodeEnum(String desc) {
        this.desc = desc;
    }
}
