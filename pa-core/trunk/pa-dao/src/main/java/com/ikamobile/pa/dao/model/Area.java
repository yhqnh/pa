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
 * 城区信息 Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class Area implements Serializable {
    
    // alias
    public static final String TABLE = "area";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of name
    public static final String FIELD_NAME = "name";
    // column name of code
    public static final String FIELD_CODE = "code";
    // column name of belongCityId
    public static final String FIELD_BELONG_CITY_ID = "belong_city_id";
    // column name of createTime
    public static final String FIELD_CREATE_TIME = "create_time";
    // column name of updateTime
    public static final String FIELD_UPDATE_TIME = "update_time";

    // columns START
    /**
     * 区域ID
     * db_column: id 
     */    
    private String id;
    /**
     * 
     * db_column: name 
     */    
    private String name;
    /**
     *  区域编码
     * db_column: code
     */
    private String code;
    /**
     * 所属城市
     * db_column: belong_city_id 
     */    
    private String belongCityId;
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
