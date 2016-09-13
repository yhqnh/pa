package com.ikamobile.pa.common.enums;

import lombok.Getter;

/**
 * Created by guest on 16/7/13.
 */
@Getter
public enum OperateLogTargetType {
    ORDER("订单"),
    TASK("任务");
    private String desc;
    OperateLogTargetType(String desc) {
        this.desc = desc;
    }

}
