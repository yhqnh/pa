package com.ikamobile.pa.dispatchapi.response;

import lombok.Data;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/8.
 */
@Data
public class FailureResponse extends BaseResponse{

    public FailureResponse() {
        this.code = -1;
        this.message = "操作失败";
    }
}
