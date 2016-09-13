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
 * 订单表 Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class Order implements Serializable {
    
    // alias
    public static final String TABLE = "order";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of code
    public static final String FIELD_CODE = "code";
    // column name of bookerId
    public static final String FIELD_BOOKER_ID = "booker_id";
    // column name of aboardLong
    public static final String FIELD_ABOARD_LONG = "aboard_long";
    // column name of aboardLati
    public static final String FIELD_ABOARD_LATI = "aboard_lati";
    // column name of aboardAreaId
    public static final String FIELD_ABOARD_AREA_ID = "aboard_area_id";
    // column name of aboardPlace
    public static final String FIELD_ABOARD_PLACE = "aboard_place";
    // column name of arrAirportId
    public static final String FIELD_ARR_AIRPORT_ID = "arr_airport_id";
    // column name of arrPlace
    public static final String FIELD_ARR_PLACE = "arr_place";
    // column name of status
    public static final String FIELD_STATUS = "status";
    // column name of flightNumber
    public static final String FIELD_FLIGHT_NUMBER = "flight_number";
    // column name of passengerNum
    public static final String FIELD_PASSENGER_NUM = "passenger_num";
    // column name of flightDepTime
    public static final String FIELD_FLIGHT_DEP_TIME = "flight_dep_time";
    // column name of expectBoardTime
    public static final String FIELD_EXPECT_BOARD_TIME = "expect_board_time";
    // column name of actualBoardTime
    public static final String FIELD_ACTUAL_BOARD_TIME = "actual_board_time";
    // column name of arrTime
    public static final String FIELD_ARR_TIME = "arr_time";
    // column name of taskId
    public static final String FIELD_TASK_ID = "task_id";
    // column name of online
    public static final String FIELD_ONLINE = "online";
    // column name of operatorId
    public static final String FIELD_OPERATOR_ID = "operator_id";
    // column name of lockKey
    public static final String FIELD_LOCK_KEY = "lock_key";
    // column name of vehicleCode
    public static final String FIELD_VEHICLE_CODE = "vehicle_code";
    // column name of voucherCode
    public static final String FIELD_VOUCHER_CODE = "voucher_code";
    // column name of createTime
    public static final String FIELD_CREATE_TIME = "create_time";
    // column name of updateTime
    public static final String FIELD_UPDATE_TIME = "update_time";

    // columns START
    /**
     * 主键id
     * db_column: id 
     */    
    private String id;
    /**
     * 订单code
     * db_column: code 
     */    
    private String code;
    /**
     * 预订人id，关联user表
     * db_column: booker_id 
     */    
    private String bookerId;
    /**
     * 上车地点经度
     * db_column: aboard_long 
     */    
    private String aboardLong;
    /**
     * 上车地点维度
     * db_column: aboard_lati 
     */    
    private String aboardLati;
    /**
     * 
     * db_column: aboard_area_id 
     */    
    private String aboardAreaId;
    /**
     * 乘客上车地点
     * db_column: aboard_place 
     */    
    private String aboardPlace;
    /**
     * 
     * db_column: arr_airport_id 
     */    
    private String arrAirportId;
    /**
     * 
     * db_column: arr_place 
     */    
    private String arrPlace;
    /**
     * 订单状态
     * db_column: status 
     */    
    private OrderStatus status;
    /**
     * 航班号
     * db_column: flight_number 
     */    
    private String flightNumber;
    /**
     * 
     * db_column: passenger_num 
     */    
    private Integer passengerNum;
    /**
     * 航班起飞时间
     * db_column: flight_dep_time 
     */    
    private Date flightDepTime;
    /**
     * 预计上车时间
     * db_column: expect_board_time
     */
    private Date expectBoardTime;
    /**
     * 确切上车时间
     * db_column: actual_board_time
     */
    private Date actualBoardTime;
    /**
     * 抵达机场时间
     * db_column: arr_time
     */
    private Date arrTime;
    /**
     *
     * db_column: task_id 
     */    
    private String taskId;
    /**
     * 是否是线上订单
     * db_column: online 
     */    
    private Boolean online;
    /**
     * 操作id
     * db_column: operator_id
     */
    private String operatorId;
    /**
     * 锁的key,非空时该订单被锁定,锁的id可以是任意字符，解锁时需要提供锁时的字符串作为解锁钥匙，只有在客户进行订单处理时订单才会被锁定，派车完成锁自动解除。
     * db_column: lock_key
     */
    private String lockKey;
    /**
     * 车辆code冗余表
     * db_column: vehicle_code
     */
    private String vehicleCode;
    /**
     * 机场寄送单单号
     * db_column: voucher_code 
     */    
    private String voucherCode;
    /**
     * 数据创建时间
     * db_column: create_time 
     */    
    private Date createTime;
    /**
     * 最新一次数据更新时间
     * db_column: update_time 
     */    
    private Date updateTime;
    // columns END

    public enum OrderStatus{

        unconfirmed(1),//待确认
        confirmed(2),//已确认
        refused(3),//已拒绝
        planned(4),//已派车
        cancelled(5),//已取消
        waiting(6),//待接
        picked(7),//已接
        missed(8),//未接异常
        served(9),//已送达
        exception(10);//异常结束

        private int value;

        private OrderStatus(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }

    }

}
