package com.ikamobile.pa.wechat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ikamobile.pa.wechat.service.result.AccessTokenInfo;
import com.ikamobile.pa.wechat.service.result.UserInfoDetail;
import com.ikamobile.pa.wechat.utils.HttpUtil;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangcheng on 2016/7/14.
 */
public class WechatServiceImpl implements WechatService {

    private static final String url_getAccessKey = "https://api.weixin.qq.com/cgi-bin/token";
    private static final String url_getUserInfo  = "https://api.weixin.qq.com/cgi-bin/user/info";
    private static final String default_lang="zh_CN";
    /**
     * 获取access_token
     *
     * @param grantType
     * @param appId
     * @param secret
     * @return
     */
    @Override
    public AccessTokenInfo getAccessToken(String grantType, String appId, String secret) {

        Map<String, String> params = new HashMap<>();
        params.put("grant_type",grantType);
        params.put("appid",appId);
        params.put("secret",secret);
        String result = HttpUtil.get(url_getAccessKey, params);
        AccessTokenInfo info = JSONObject.parseObject(result,AccessTokenInfo.class);
        return info;
    }

    /**
     * 获取用户基本信息
     *
     * @param accessToken
     * @param openId
     * @return
     */
    @Override
    public UserInfoDetail getUserBaseInfo(String accessToken, String openId) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token",accessToken);
        params.put("openid",openId);
        params.put("lang",default_lang);
        String result = HttpUtil.get(url_getUserInfo, params);
        UserInfoDetail detail = JSONObject.parseObject(result,UserInfoDetail.class);
        return detail;
    }
}
