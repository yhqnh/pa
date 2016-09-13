package com.ikamobile.pa.common.enums;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/6.
 */
public enum  CertificateTypeEnum {

    IDENTITY_CARD("身份证"),
    PASSPORT("护照"),
    OTHERS("其他"),
    HKM_PASS("港澳通行证"),
    TW_PASS("台湾通行证"),
    MTPs("台胞证"),
    HOME_RETURN_PERMIT("返乡证");

    private String desc;

    CertificateTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
