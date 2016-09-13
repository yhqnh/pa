package com.ikamobile.pa.clientsapi.controller.result;

import lombok.Data;

/**
 * Created by guest on 16/7/11.
 */
@Data
public class OperateLog {
    /**
     * 主键
     */
    private String id;
    /**
     * order：订单  task：任务
     */
    private String targetType;
    /**
     * 目标ID
     */
    private String targetId;
    /**
     * 操作人员名称
     */
    private String operator;
    /**
     * 操作内容
     */
    private String operation;
    /**
     * 日志显示级别 0:调度可见，1：司机可见，2：乘客可见
     */
    private Integer level;
    /**
     * 创建时间
     */
    private Long createTime;
}
