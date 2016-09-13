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
public class VehicleTraffic implements Serializable {
    
    // alias
    public static final String TABLE = "vehicle_traffic";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of week
    public static final String FIELD_WEEK = "week";
    // column name of limitNumber
    public static final String FIELD_LIMIT_NUMBER = "limit_number";
    // column name of createTime
    public static final String FIELD_CREATE_TIME = "create_time";
    // column name of updateTime
    public static final String FIELD_UPDATE_TIME = "update_time";

    // columns START
    /**
     * 主键
     * db_column: id 
     */    
    private String id;
    /**
     * 星期几
     * db_column: week 
     */    
    private String week;
    /**
     * 限行尾号
     * db_column: limit_number 
     */    
    private String limitNumber;
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
