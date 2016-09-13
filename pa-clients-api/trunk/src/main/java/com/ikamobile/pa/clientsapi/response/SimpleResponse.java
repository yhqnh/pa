package com.ikamobile.pa.clientsapi.response;

import lombok.Data;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/7.
 */
@Data
public class SimpleResponse<T> extends BaseResponse {

    private T data;

    public SimpleResponse() {
        this.code = 0;
        this.message = "操作成功";
    }
    public SimpleResponse(int code ,String message) {
        this.code = code;
        this.message = message;
    }
}
