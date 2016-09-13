package com.ikamobile.pa.clientsapi.controller.param;

import lombok.Data;

/**
 * Created by zhangcheng on 2016/7/11.
 */
@Data
public class PageParam<T> {

    /**
     * 当前页数
     */
    public int pageNum;

    /**
     * 每页条数
     */
    public int pageSize;

    /**
     * 其他查询条件
     */
    public T searchParam;
}
