package com.ikamobile.pa.dispatchapi.response;

import lombok.Data;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/8.
 */
@Data
public class ErrorResponse extends BaseResponse{

    public ErrorResponse() {
        this.code = 500;
        this.message = "系统繁忙，请稍后重试";
    }
}
