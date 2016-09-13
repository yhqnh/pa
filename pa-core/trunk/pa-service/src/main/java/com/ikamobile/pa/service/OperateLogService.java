package com.ikamobile.pa.service;

import com.ikamobile.pa.common.enums.OperateLogTargetType;
import com.ikamobile.pa.thrift.common.OperateLogDto;

import java.util.List;

/**
 * Created by zhangcheng on 2016/7/13.
 */
public interface OperateLogService{
    List<OperateLogDto> listOperateLogs(OperateLogTargetType targetType,String targetId,Integer level);
    boolean saveOperateLog(OperateLogTargetType targetType,String targetId,Integer level,String message,String operatorName);

    /**
     * 存储 仅后台可见 订单日志
     * @return
     */
    boolean orderBack(String orderId,String message,String operatorName);

    /**
     *  存储 中台（司机），后台（dispacther）可见日志
     */
    boolean orderMid(String orderId,String message,String operatorName);

    /**
     *  存储 所有人能看的日志
     */
    boolean orderFront(String orderId,String message,String operatorName);
}
