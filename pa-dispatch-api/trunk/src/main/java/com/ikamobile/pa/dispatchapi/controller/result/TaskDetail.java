package com.ikamobile.pa.dispatchapi.controller.result;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangcheng on 2016/7/7.
 */
@Data
public class TaskDetail {
    /**
     * 任务ID
     */
    private String id;
    /**
     * 任务编号
     */
    private String code;
    /**
     * 状态 1-未开始 2-进行中 3-已结束
     */
    private Integer status;
    /**
     * 司机信息
     */
    private Driver driver;
    /**
     * 分配的车辆信息
     */
    private Vehicle vehicle;
    /**
     * 乘客数量
     */
    private Integer psgCount;
    /**
     * 最早起飞时间
     */
    private Long earlistFlightTime;
    /**
     * 任务开始时间
     */
    private Long startTime;
    /**
     * 任务完成时间
     */
    private Long finishTime;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 任务中的订单详情
     */
    private List<OrderDetail> orders;


}
