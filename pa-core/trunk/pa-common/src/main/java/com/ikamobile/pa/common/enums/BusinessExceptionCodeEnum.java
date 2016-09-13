package com.ikamobile.pa.common.enums;

import lombok.Getter;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/5.
 */
@Getter
public enum BusinessExceptionCodeEnum {

    NOT_EXSITS(1, "不存在"),
    ORDER_NOT_EXISTS(11,"订单不存在"),
    NOT_LOGIN_EXCEPTION(2, "未登录"),
    DATA_CHANGED(6, "数据已修改"),

    ACCOUNT_MULTI_USERNAME_EXCEPTION(3, "账号异常，存在多个用户名相同的用户"),
    STATUS_EX(4,"状态异常"),
    ORDER_STATUS_EX(41,"订单状态异常"),
    ORDER_DATA_CHANGED(61,"订单已修改"),

    USER_NAME_EX(51,"登录名错误或不存在"),
    USER_PASSWORD_EX(52,"密码错误"),
    VERITYCODE_INVALID(6,"验证码不合法"),
    BUSSINESS_EXCEPTION(500, "未知异常");

    private int code;
    private String message;

    BusinessExceptionCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

