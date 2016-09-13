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
 * 司机表 Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class Driver implements Serializable {
    
    // alias
    public static final String TABLE = "driver";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of loginName
    public static final String FIELD_LOGIN_NAME = "login_name";
    // column name of password
    public static final String FIELD_PASSWORD = "password";
    // column name of name
    public static final String FIELD_NAME = "name";
    // column name of phoneNumber
    public static final String FIELD_PHONE_NUMBER = "phone_number";
    // column name of phoneNumberOther
    public static final String FIELD_PHONE_NUMBER_OTHER = "phone_number_other";
    // column name of cityId
    public static final String FIELD_CITY_ID = "city_id";
    // column name of area
    public static final String FIELD_AREA = "area";
    // column name of sex
    public static final String FIELD_SEX = "sex";
    // column name of age
    public static final String FIELD_AGE = "age";
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
     * 登陆名
     * db_column: login_name 
     */    
    private String loginName;
    /**
     * 密码
     * db_column: password 
     */    
    private String password;
    /**
     * 姓名
     * db_column: name 
     */    
    private String name;
    /**
     * 手机号
     * db_column: phone_number 
     */    
    private String phoneNumber;
    /**
     * 其他联系号码
     * db_column: phone_number_other 
     */    
    private String phoneNumberOther;
    /**
     * 所在城市
     * db_column: city_id 
     */    
    private String cityId;
    /**
     * 所在区
     * db_column: area 
     */    
    private String area;
    /**
     * 性别
     * db_column: sex 
     */    
    private String sex;
    /**
     * 年龄
     * db_column: age 
     */    
    private Integer age;
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
