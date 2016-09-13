package com.ikamobile.pa.dispatchapi.controller.param;

import lombok.Data;

import java.util.List;

/**
 * Created by guest on 16/7/8.
 */
@Data
public class OrderQueryPram {

    /**
     * 订单创建时间范围 beginTime
     */
    private Long createTimeFloor;
    /**
     * 订单创建时间范围，结束时间
     */
    private Long createTimeTop;

    /** 订单状态
     */
    private List<String> status;

    /**
     * 操作人Id
     */
    private String operatorId;

    /**
     * 司机自编号,实际使用的是车辆code
     */
    private String driverId ;

    /**
     * 预订人手机号
     */
    private String bookerMobile;
    /**
     * 航班号
     */
    private String flightNumber;
    /**
     * 起飞时间下限时间
     */
    private Long flightDepTimeFloor;
    /**
     * 起飞时间上限时间
     */
    private Long flightDepTimeTop;
    /**
     * 是否是线上订单，Y=》是，N=>不是，不填，全部订单
     */
    private String forOnline;
    /**
     * 铁航接送机 凭据单号
     */
    private String voucherCode;


}
