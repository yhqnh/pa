package com.ikamobile.pa.wechat.service.result;

import lombok.Data;

/**
 * Created by zhangcheng on 2016/7/14.
 */
@Data
public class AccessTokenInfo {

    private String access_token;//凭证

    private String expire_in;//有效时间，单位：秒
}
