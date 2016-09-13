package com.ikamobile.pa.common.enums;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/6.
 */
public enum SexEnum {

    MALE("男"),
    FEMALE("女");

    private int value;

    private String desc;

    SexEnum( String desc) {
        this.desc = desc;
    }
}
