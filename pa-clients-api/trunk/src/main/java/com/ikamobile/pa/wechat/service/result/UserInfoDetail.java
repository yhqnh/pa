package com.ikamobile.pa.wechat.service.result;

import lombok.Data;

/**
 * Created by zhangcheng on 2016/7/14.
 */
@Data
public class UserInfoDetail {

    private int subscribe;//是否订阅

    private String openId;//用户对当前公众号的唯一标识

    private String nickName;//昵称

    private int sex;//1：男  2：女  0：未知

    private String city;//城市

    private String country;//国家

    private String province;//省份

    private String language;//用户的语言，简体中文为zh_CN

    private String headimgurl;//用户头像

    private String subscribe_time;//用户关注时间，为时间戳

    private String unionid;//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段

    private String remark;// 公众号运营者对粉丝的备注

    private String groupid;//用户所在的分组ID
}
