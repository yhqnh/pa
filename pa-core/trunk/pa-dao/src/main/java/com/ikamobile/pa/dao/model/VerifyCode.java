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
 *  Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class VerifyCode implements Serializable {
    
    // alias
    public static final String TABLE = "verify_code";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of type
    public static final String FIELD_TYPE = "type";
    // column name of phoneNumber
    public static final String FIELD_PHONE_NUMBER = "phone_number";
    // column name of verifyCode
    public static final String FIELD_VERIFY_CODE = "verify_code";
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
     * 类型（CREATE_ORDER-创建订单，REGISTER-注册）
     * db_column: type 
     */    
    private String type;
    /**
     * 手机号
     * db_column: phone_number 
     */    
    private String phoneNumber;
    /**
     * 验证码
     * db_column: verify_code 
     */    
    private String verifyCode;
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
