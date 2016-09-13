/*
 * Powered By ikamobile
 * Web Site: http://www.ikamobile.cn
 */
package com.ikamobile.pa.clientsapi.controller.result;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @version 1.0
 * @since 1.0
 *
 */
@Data
public class Driver implements Serializable {

    /**
     * uuid id
     */
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 其他联系号码
     */
    private String phoneNumberOther;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;

}
