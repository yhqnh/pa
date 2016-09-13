package com.ikamobile.pa.clientsapi.response;

import lombok.Data;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/15.
 */
@Data
public class NotLoginResponse extends BaseResponse{

    public NotLoginResponse() {
        this.code = 602;
        this.message = "未登录";
    }
}
