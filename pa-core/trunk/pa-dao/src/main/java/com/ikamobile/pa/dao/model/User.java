/*
 * Powered By ikamobile
 * Web Site: http://www.ikamobile.cn
 */
package com.ikamobile.pa.dao.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;

/**
 * 
 * 用户表 Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class User implements Serializable {
    
    // alias
    public static final String TABLE = "user";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of phoneNumber
    public static final String FIELD_PHONE_NUMBER = "phone_number";
    // column name of password
    public static final String FIELD_PASSWORD = "password";
    // column name of weixinOpenId
    public static final String FIELD_WEIXIN_OPEN_ID = "weixin_open_id";
    // column name of deviceNo
    public static final String FIELD_DEVICE_NO = "device_no";
    // column name of salt
    public static final String FIELD_SALT = "salt";
    // column name of createTime
    public static final String FIELD_CREATE_TIME = "create_time";
    // column name of updateTime
    public static final String FIELD_UPDATE_TIME = "update_time";

    // columns START
    /**
     * 
     * db_column: id 
     */    
    private String id;
    /**
     * 手机号
     * db_column: phone_number 
     */    
    private String phoneNumber;
    /**
     * 密码
     * db_column: password
     */
    private String password;
    /**
     * 微信开放平台id
     * db_column: weixin_open_id 
     */    
    private String weixinOpenId;
    /**
     * 设备序列号
     * db_column: device_no 
     */    
    private String deviceNo;
    /**
     * 调料
     * db_column: salt 
     */    
    private String salt;
    /**
     * 创建时间
     * db_column: create_time 
     */    
    private Date createTime;
    /**
     * 更新时间
     * db_column: update_time 
     */    
    private Date updateTime;
    // columns END


}
