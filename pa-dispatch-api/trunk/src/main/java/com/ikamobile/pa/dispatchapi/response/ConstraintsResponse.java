package com.ikamobile.pa.dispatchapi.response;

import lombok.Data;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/15.
 */
@Data
public class ConstraintsResponse extends BaseResponse{

    public ConstraintsResponse() {
        this.code = 700;
        this.message = "参数异常";
    }
}
