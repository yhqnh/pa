package com.ikamobile.pa.dispatchapi.controller.param;

import com.ikamobile.pa.dispatchapi.controller.result.Position;
import lombok.Data;

import java.util.Date;

/**
 * Created by guest on 16/7/8.
 */
@Data
public class OrderUpdateParam {


    /**
     * 上车地点
     */
    private Position aboardPosition;



    /**
     * 航班起飞时间
     */
    private Date flightDepTime;

    /**
     * 订单状态
     */
    private String Status;

    /**
     * 预计上车时间
     */
    private Date expectBoardTime;


}
