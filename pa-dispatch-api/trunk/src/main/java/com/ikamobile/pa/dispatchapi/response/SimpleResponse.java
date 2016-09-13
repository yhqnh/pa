package com.ikamobile.pa.dispatchapi.response;

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
}
