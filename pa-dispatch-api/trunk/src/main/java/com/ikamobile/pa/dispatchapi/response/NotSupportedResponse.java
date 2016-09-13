package com.ikamobile.pa.dispatchapi.response;

import lombok.Data;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/15.
 */
@Data
public class NotSupportedResponse extends BaseResponse{

    public NotSupportedResponse() {
        this.code = 800;
        this.message = "此方法不支持";
    }
}
