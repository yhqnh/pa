/*
 * Powered By ikamobile
 * Web Site: http://www.ikamobile.cn
 */
package com.ikamobile.pa.dao.model;

import com.ikamobile.pa.common.enums.OperateLogTargetType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 操作日志 Database Model
 * Generated automaticly
 * @version 1.0
 * @since 1.0
 *
 */
@Data
@SuppressWarnings("serial")
public class OperateLog implements Serializable {

    public final static int LEVEL_DISPATCHER_SEE = 0;
    public final static int LEVEL_DRIVER_SEE = 1;
    public final static int LEVEL_USER_SEE = 2;

    // alias
    public static final String TABLE = "operate_log";
    // column name of id
    public static final String FIELD_ID = "id";
    // column name of targetType
    public static final String FIELD_TARGET_TYPE = "target_type";
    // column name of targetId
    public static final String FIELD_TARGET_ID = "target_id";
    // column name of operator
    public static final String FIELD_OPERATOR = "operator";
    // column name of operation
    public static final String FIELD_OPERATION = "operation";
    // column name of level
    public static final String FIELD_LEVEL = "level";
    // column name of createTime
    public static final String FIELD_CREATE_TIME = "create_time";

    // columns START
    /**
     * 主键
     * db_column: id 
     */    
    private String id;
    /**
     * order：订单  task：任务
     * db_column: target_type 
     */    
    private OperateLogTargetType targetType;
    /**
     * 目标ID
     * db_column: target_id 
     */    
    private String targetId;
    /**
     * 操作人员名称
     * db_column: operator 
     */    
    private String operator;
    /**
     * 操作内容
     * db_column: operation 
     */    
    private String operation;
    /**
     * 日志显示级别 0:调度可见，1：司机可见，2：乘客可见
     * db_column: level 
     */    
    private Integer level;
    /**
     * 创建时间
     * db_column: create_time 
     */    
    private Date createTime;
    // columns END



}
