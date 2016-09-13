package com.ikamobile.pa.clientsapi.controller.param;

import com.ikamobile.pa.clientsapi.controller.result.Passenger;
import com.ikamobile.pa.clientsapi.controller.result.Position;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by guest on 16/7/8.
 */
@Data
public class OrderCreateParam {

    /**
     * 预订人手机号
     */
    @NotBlank
    private String mobile;

    /**
     * 手机验证码
     */
    @NotBlank
    private String verificationCode;

    /**
     * 乘客信息 <b>至少上传 姓名、证件类型、证件号码</b>
     */
    @NotNull
    @Valid
    private List<Passenger> passengers;

    /**
     * 上车地点
     */
    @NotNull
    @Valid
    private Position aboardPosition;

    /**
     * 下车地点
     */
    private Position arrPosition;

    /**
     * 航班号
     */
    @NotBlank
    private String flightNumber;

    /**
     * 航班起飞时间 ，时间使用时间戳字符串
     */
    @NotNull
    private Long flightDepTime;

    /**
     * 铁航接送单单号 <b>非必填</b>
     */
    private String voucherCode;



}
