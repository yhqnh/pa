package com.ikamobile.pa.dispatchapi.controller.result;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhangcheng on 2016/7/8.
 */
@Data
public class TaskListDetail {

    /**
     * id
     */
    private String id;

    /**
     * code
     */
    private String code;

    /**
     * 状态
     */
    private String status;

    /**
     * 订单数量
     */
    private int orderCount;

    /**
     * 人员数量
     */
    private int passageCount;

    /**
     * 最早起飞时间
     */
    private Date earliestFlightTime ;

    /**
     * 任务开始时间
     */
    private Date startTime;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 车牌号码
     */
    private String vehicleNo;

    /**
     * 车辆ID
     */
    private String vehicleId;

    /**
     * 司机名称
     */
    private String driverName;

    /**
     * 司机ID
     */
    private String driverId;

    /**
     * 司机联系电话
     */
    private String driverTel;

    /**
     * 司机其他联系方式
     */
    private String driverOtherTel;

}
