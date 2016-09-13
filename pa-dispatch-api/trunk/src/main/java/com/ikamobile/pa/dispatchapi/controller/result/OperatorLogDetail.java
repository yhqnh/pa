package com.ikamobile.pa.dispatchapi.controller.result;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhangcheng on 2016/7/11.
 */
@Data
public class OperatorLogDetail {

    private String id;

    private String OperatorName;

    private String operation;

    private int level;

    private Date createTime;

}
