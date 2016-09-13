package com.ikamobile.pa.dispatchapi.controller.result;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhangcheng on 2016/7/11.
 */
@Data
public class Dispatcher {

    /**
     * ID
     * db_column: id
     */
    private String id;
    /**
     * 登录名
     * db_column: login_name
     */
    private String loginName;
    /**
     * 手机号
     * db_column: phone_number
     */
    private String phoneNumber;
    // columns END
}
