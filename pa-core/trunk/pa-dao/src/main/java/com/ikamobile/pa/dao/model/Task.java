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
 * 任务表 Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class Task implements Serializable {
    
    // alias
    public static final String TABLE = "task";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of code
    public static final String FIELD_CODE = "code";
    // column name of status
    public static final String FIELD_STATUS = "status";
    // column name of driverId
    public static final String FIELD_DRIVER_ID = "driver_id";
    // column name of driverName
    public static final String FIELD_DRIVER_NAME = "driver_name";
    // column name of vehicleId
    public static final String FIELD_VEHICLE_ID = "vehicle_id";
    // column name of vehicleNum
    public static final String FIELD_VEHICLE_NUM = "vehicle_num";
    // column name of psgCount
    public static final String FIELD_PSG_COUNT = "psg_count";
    // column name of earlistFlightTime
    public static final String FIELD_EARLIST_FLIGHT_TIME = "earlist_flight_time";
    // column name of startTime
    public static final String FIELD_START_TIME = "start_time";
    // column name of finishTime
    public static final String FIELD_FINISH_TIME = "finish_time";
    // column name of createTime
    public static final String FIELD_CREATE_TIME = "create_time";
    // column name of updateTime
    public static final String FIELD_UPDATE_TIME = "update_time";

    // columns START
    /**
     * 任务编号
     * db_column: id 
     */    
    private String id;
    /**
     * 任务编号
     * db_column: code 
     */    
    private String code;
    /**
     * 状态 1-未开始 2-进行中 3-已结束
     * db_column: status 
     */    
    private Integer status;
    /**
     * 司机ID
     * db_column: driver_id 
     */    
    private String driverId;
    /**
     * 司机姓名
     * db_column: driver_name 
     */    
    private String driverName;
    /**
     * 分配的车辆ID
     * db_column: vehicle_id 
     */    
    private String vehicleId;
    /**
     * 车牌号
     * db_column: vehicle_num 
     */    
    private String vehicleNum;
    /**
     * 乘客数量
     * db_column: psg_count 
     */    
    private Integer psgCount;
    /**
     * 订单数量
     * db_column: order_count
     */
    private Integer orderCount;

    /**
     * 最早起飞时间
     * db_column: earlist_flight_time 
     */    
    private Date earlistFlightTime;
    /**
     * 任务开始时间
     * db_column: start_time 
     */    
    private Date startTime;
    /**
     * 任务完成时间
     * db_column: finish_time 
     */    
    private Date finishTime;
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




    public enum TaskStatus{
        ready(1),//为开始
        carry(2),//进行中
        finish(3);//完成

        private int value;

        private TaskStatus(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }
    }

}
