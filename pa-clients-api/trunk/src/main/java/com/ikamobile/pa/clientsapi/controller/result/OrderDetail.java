package com.ikamobile.pa.clientsapi.controller.result;

import lombok.Data;

import java.util.List;

/**
 * Created by guest on 16/7/8.
 */
@Data
public class OrderDetail {

    //订单基础信息
    /**
     * 主键id
     */
    private String id;
    /**
     * 订单code
     */
    private String code;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 订单状态名称
     */
    private String statusName;

    //预订人，乘客信息
    /*
     * 预订人id，关联user表
     */
    private User booker;

    /**
     * 订单联系人
     */
    private List<Passenger> passengers;


    /**
     * 上车地点
     */
    private Position aboardPosition;

    /**
     * 抵达地点
     */
    private Position arrPosition;


    //航班信息
    /**
     * 航班号
     */
    private String flightNumber;

    /**
     * 航班起飞时间
     */
    private Long flightDepTime;


    //派车信息
    //
    /**
     * 车辆信息
     */
    private Vehicle vehicle;

    /**
     * 司机信息
     */
    private Driver driver;

    /**
     * 是否是线上订单
     */
    private Boolean online;

    /**
     * 铁航接送单单号
     */
    private String voucherCode;


    //旅程信息
    /**
     * expect_board_time
     * 预计上车时间
     */
    private Long expectBoardTime;

    /**
     * actual_board_time
     * 确切上车时间
     */
    private Long actualBoardTime;

    /**
     * 抵达机场时间
     */
    private Long arrTime;

    /**
     * 下单时间
     */
    private Long createTime;
    //todo 添加状态描述

    /**
     * 操作日志
     */
    List<OperateLog> operateLogs;

    /**
     * 订单状态说明
     */
    private String explaination;

}
