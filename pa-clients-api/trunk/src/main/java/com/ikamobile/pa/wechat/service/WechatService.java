package com.ikamobile.pa.wechat.service;

import com.ikamobile.pa.wechat.service.result.AccessTokenInfo;
import com.ikamobile.pa.wechat.service.result.UserInfoDetail;

/**
 * Created by zhangcheng on 2016/7/14.
 */
public interface WechatService {

    /**
     * 获取access_token
     * 每两小时刷新
     * @param grantType
     * @param appId
     * @param secret
     * @return
     */
    public AccessTokenInfo getAccessToken(String grantType, String appId, String secret);

    /**
     * 获取用户基本信息
     * @param accessToken
     * @param openId
     * @return
     */
    public UserInfoDetail getUserBaseInfo(String accessToken, String openId);
}
