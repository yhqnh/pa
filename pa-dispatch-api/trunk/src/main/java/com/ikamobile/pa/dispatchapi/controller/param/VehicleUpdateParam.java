package com.ikamobile.pa.dispatchapi.controller.param;

import lombok.Data;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/11.
 */
@Data
public class VehicleUpdateParam {

    private String id;

    /**
     * MEET-接机，TRANSPORT-送机
     */
    private String type;

    /**
     * ENABLE-可用，DISABLE-不可用
     */
    private String status;
}
