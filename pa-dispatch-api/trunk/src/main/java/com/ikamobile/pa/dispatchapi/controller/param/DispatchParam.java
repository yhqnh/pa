package com.ikamobile.pa.dispatchapi.controller.param;

import com.ikamobile.pa.dispatchapi.controller.result.Position;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by guest on 16/7/8.
 */
@Data
public class DispatchParam {


    /**
     * 上车地点
     */

    private Position aboardPosition;


    /**
     * 航班起飞时间
     */

    private Long flightDepTime;

    /**
     * 预计上车时间
     */
    @NotNull
    private Long expectBoardTime;

    /**
     * 车辆自编号
     */
    @NotBlank
    private String vehicleCode;

    /**
     * 订单id
     */
    @NotBlank
    private String orderId;

}

