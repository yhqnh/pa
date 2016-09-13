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
 * 车辆信息表 Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class Vehicle implements Serializable {
    
    // alias
    public static final String TABLE = "vehicle";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of code
    public static final String FIELD_CODE = "code";
    // column name of number
    public static final String FIELD_NUMBER = "number";
    // column name of type
    public static final String FIELD_TYPE = "type";
    // column name of driverId
    public static final String FIELD_DRIVER_ID = "driver_id";
    // column name of seats
    public static final String FIELD_SEATS = "seats";
    // column name of seats
    public static final String FIELD_STATUS = "status";
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
     * 自编码
     * db_column: code 
     */    
    private String code;
    /**
     * 车牌号
     * db_column: number 
     */    
    private String number;
    /**
     * MEET-接机，TRANSPORT-送机
     * db_column: type 
     */    
    private String type;
    /**
     * ENABLE-可用，DISABLE-不可用
     * db_column: type
     */
    private String status;
    /**
     * 司机外键
     * db_column: driver_id 
     */    
    private String driverId;
    /**
     * 座位数
     * db_column: seats 
     */    
    private Integer seats;
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
