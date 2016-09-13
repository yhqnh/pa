package com.ikamobile.pa.dispatchapi.controller.param;

import lombok.Data;

/**
 * Created by zhangcheng on 2016/7/11.
 */
@Data
public class PageParam {

    public static final int DEFAULT_PAGE_INDEX = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 当前页数 默认第一页
     */
    public int pageIndex = DEFAULT_PAGE_INDEX;

    /**
     * 每页条数 默认20条
     */
    public int pageSize = DEFAULT_PAGE_SIZE;

}
