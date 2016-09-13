package com.ikamobile.pa.dispatchapi.response;

import lombok.Data;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/15.
 */
@Data
public class IncorrectCredentialsResponse extends BaseResponse{

    public IncorrectCredentialsResponse() {
        this.code = 601;
        this.message = "密码错误";
    }
}
