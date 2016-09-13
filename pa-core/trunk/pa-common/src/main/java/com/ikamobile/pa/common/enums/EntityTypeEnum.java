package com.ikamobile.pa.common.enums;

/**
 * Created by zhangcheng on 2016/7/13.
 */
public enum EntityTypeEnum {
    TASK("T"),ORDER("O"),VEHICLE("V"),DRIVER("D");

    private String prefix;

    private EntityTypeEnum(String prefix){
        this.prefix = prefix;
    }

    public String getPrefix(){
        return this.prefix;
    }
}
