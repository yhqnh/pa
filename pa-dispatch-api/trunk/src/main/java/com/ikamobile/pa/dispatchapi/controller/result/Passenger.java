package com.ikamobile.pa.dispatchapi.controller.result;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by guest on 16/7/8.
 */
@Data
public class Passenger {
    private String id;
    /**
     * 姓名
     */
    @NotBlank
    private String name;
    /**
     * 性别（0-未知，1-男，2-女） todo 换枚举
     */
    private Integer gender;
    /**
     * 证件类型（NI-身份证）
     */
    @NotBlank
    @Pattern(regexp = "IDENTITY_CARD|PASSPORT|OTHERS|TW_PASS|MTPs|HOME_RETURN_PERMIT|HKM_PASS")
    private String certificateType;

    //增加类型描述
    private String certificateTypeDesc;
    /**
     * 证件号码
     */
    @NotBlank
    private String certificateNumber;
    /**
     * 生日
     */
    private long birthday;
    /**
     * 订单关联id
     */
    private String orderId;
    /**
     * 年龄
     */
    private Integer age;
}
