/*
 * Powered By ikamobile
 * Web Site: http://www.ikamobile.cn
 */
package com.ikamobile.pa.clientsapi.controller.result;

import lombok.Data;

import java.io.Serializable;

@Data
@SuppressWarnings("serial")
public class Vehicle implements Serializable {
    /**
     *
     *  id
     */
    private String id;
    /**
     * 自编码
     */
    private String code;
    /**
     * 车牌号  `
     */
    private String number;
    /**送机
     * MEET-接机，TRANSPORT-送机
     */
    private String type;

    /**
     * 座位数
     */
    private Short seats;


}
