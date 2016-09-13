package com.ikamobile.pa.clientsapi.controller.result;

import lombok.Data;

/**
 * Created by zhangcheng on 2016/7/11.
 */
@Data
public class CertificateType {

    /**
     * 证件code
     */
    public String code;

    /**
     * 中文描述
     */
    public String desc;

    /**
     * 正则表达式
     */
    public String regex;
}
