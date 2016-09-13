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
 * 调度者表 Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class Dispatcher implements Serializable {
    
    // alias
    public static final String TABLE = "dispatcher";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of loginName
    public static final String FIELD_LOGIN_NAME = "login_name";
    // column name of password
    public static final String FIELD_PASSWORD = "password";
    // column name of phoneNumber
    public static final String FIELD_PHONE_NUMBER = "phone_number";
    // column name of salt
    public static final String FIELD_SALT = "salt";
    // column name of createTime
    public static final String FIELD_CREATE_TIME = "create_time";
    // column name of updateTime
    public static final String FIELD_UPDATE_TIME = "update_time";

    // columns START
    /**
     * ID
     * db_column: id 
     */    
    private String id;
    /**
     * 登录名
     * db_column: login_name 
     */    
    private String loginName;
    /**
     * 密码
     * db_column: password 
     */    
    private String password;
    /**
     * 手机号
     * db_column: phone_number 
     */    
    private String phoneNumber;
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
