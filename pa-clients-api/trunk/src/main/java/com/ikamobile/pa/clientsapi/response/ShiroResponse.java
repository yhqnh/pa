package com.ikamobile.pa.clientsapi.response;

import lombok.Data;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/8.
 */
@Data
public class ShiroResponse extends BaseResponse{

    public ShiroResponse() {
        this.code = 600;
        this.message = "用户异常，请稍后重试";
    }
}
