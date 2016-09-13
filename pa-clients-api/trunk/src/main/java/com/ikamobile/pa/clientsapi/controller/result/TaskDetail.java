package com.ikamobile.pa.clientsapi.controller.result;

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
     * 乘客数量
     */
    private Integer psgCount;
    /**
     * 最早起飞时间
     */
    private long earliestFlightTime;
    /**
     * 任务开始时间
     */
    private long startTime;
    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 任务中的订单详情
     */
    private List<OrderDetail> orders;

}
