package com.ikamobile.pa.dispatchapi.response;

import lombok.Data;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/15.
 */
@Data
public class UnknownAccountResponse extends BaseResponse{

    public UnknownAccountResponse() {
        this.code = 602;
        this.message = "用户不存在";
    }
}
