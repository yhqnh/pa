package com.ikamobile.pa.dispatchapi.controller.param;

import lombok.Data;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/11.
 */
@Data
public class VehicleParam {

    /**
     * db_column: id
     */
    private String id;
    /**
     * 自编码
     * db_column: code
     */
    private String code;
    /**
     * 车牌号
     * db_column: number
     */
    private String number;
    /**
     * MEET-接机，TRANSPORT-送机
     * db_column: type
     */
    private String type;
    /**
     * 座位数
     * db_column: seats
     */
    private Integer seats;
}
