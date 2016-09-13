package com.ikamobile.pa.clientsapi.response;

import lombok.Data;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/15.
 */
@Data
public class InvalidSessionResponse extends BaseResponse{

    public InvalidSessionResponse() {
        this.code = 603;
        this.message = "登陆已过期，请重新登陆";
    }
}
