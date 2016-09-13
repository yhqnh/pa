package com.ikamobile.pa.clientsapi.controller.result;

import lombok.Data;

/**
 * Created by zhangcheng on 2016/7/11.
 */
@Data
public class AreaDetail {

    /**
     * 区域ID
     */
    private String id;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 区域编码
     */
    private String code;

    /**
     * 所属城市
     */
    private String belongCityName;


}
