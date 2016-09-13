package com.ikamobile.pa.clientsapi.controller.result;

import lombok.Data;

/**
 * Created by guest on 16/7/8.
 */
@Data
public class User {
    // columns START
    /**
     * id
     */
    private String id;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 微信开放平台id
     */
    private String weixinOpenId;
    /**
     * 设备序列号
     */
    private String deviceNo;

}
