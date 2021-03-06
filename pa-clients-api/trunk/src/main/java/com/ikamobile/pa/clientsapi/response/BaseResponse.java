package com.ikamobile.pa.clientsapi.response;

import lombok.Data;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/3.
 */
@Data
public class BaseResponse {

    /**
     * code 信息
     */
    protected int code;

    /**
     * 响应消息
     */
    protected String message;
}
