package com.ikamobile.pa.clientsapi.controller.param;

import com.ikamobile.pa.clientsapi.controller.result.Passenger;
import com.ikamobile.pa.clientsapi.controller.result.Position;
import lombok.Data;

import java.util.List;

/**
 * Created by guest on 16/7/8.
 */
@Data
public class OrderQueryParam {

    /**
     * 预订人手机号
     */
    private String mobile;

    /**
     * 乘客信息 <b>至少上传 姓名、证件类型、证件号码</b>
     */
    private List<Passenger> passengers;

    /**
     * 上车地点
     */
    private Position aboardPosition;

    /**
     * 下车地点
     */
    private Position arrPosition;

    /**
     * 航班号
     */
    private String flightNumber;

    /**
     * 航班起飞时间
     */
    private long flightDepTime;

    /**
     * 铁航接送单单号 <b>非必填</b>
     */
    private String voucherCode;


}
