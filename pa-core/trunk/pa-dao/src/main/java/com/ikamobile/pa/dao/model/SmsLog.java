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
 * 短信记录表 Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class SmsLog implements Serializable {
    
    // alias
    public static final String TABLE = "sms_log";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of type
    public static final String FIELD_TYPE = "type";
    // column name of content
    public static final String FIELD_CONTENT = "content";
    // column name of mobile
    public static final String FIELD_MOBILE = "mobile";
    // column name of createTime
    public static final String FIELD_CREATE_TIME = "create_time";

    // columns START
    /**
     * 主键id
     * db_column: id 
     */    
    private String id;
    /**
     * 类型（登陆验证码，确认订单，新增车辆，手机校验）
     * db_column: type 
     */    
    private String type;
    /**
     * 短信内容
     * db_column: content 
     */    
    private String content;
    /**
     * 接收者电话
     * db_column: mobile 
     */    
    private String mobile;
    /**
     * 创建时间
     * db_column: create_time 
     */    
    private Date createTime;
    // columns END


}
