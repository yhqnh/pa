/*
 * Powered By ikamobile
 * Web Site: http://www.ikamobile.cn
 */
package com.ikamobile.pa.dao.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 城市信息 Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class City implements Serializable {
    
    // alias
    public static final String TABLE = "city";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of cityName
    public static final String FIELD_CITY_NAME = "city_name";
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
     * 
     * db_column: city_name 
     */    
    private String cityName;
    /**
     * 
     * db_column: create_time 
     */    
    private Date createTime;
    /**
     * 
     * db_column: update_time 
     */    
    private Date updateTime;
    // columns END


}
