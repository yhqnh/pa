package com.ikamobile.pa.dispatchapi.controller.result;

import lombok.Data;

/**
 * Created by guest on 16/7/8.
 */
@Data
public class Contact {
    // columns START
    /**
     *
     * db_column: id
     */
    private String id;
    /**
     * 手机号
     * db_column: phone_number
     */
    private String phoneNumber;

    /**
     * 联系人姓名
     */
    private String name;


}
