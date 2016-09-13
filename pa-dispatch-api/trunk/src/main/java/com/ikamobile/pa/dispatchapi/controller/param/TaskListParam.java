package com.ikamobile.pa.dispatchapi.controller.param;

import lombok.Data;

/**
 * Created by zhangcheng on 2016/7/11.
 */
@Data
public class TaskListParam extends PageParam {

    /**
     * 操作人员ID
     */
    private String operatorId;

}
