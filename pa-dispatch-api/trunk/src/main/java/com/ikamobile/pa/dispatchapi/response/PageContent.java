package com.ikamobile.pa.dispatchapi.response;

import lombok.Data;

import java.util.List;

/**
 * Created by zhangcheng on 2016/7/11.
 */
@Data
public class PageContent<T> {

    /**
     * 数据列表
     */
    private List<T> pageContent;

    /**
     * 总条数
     */
    private int totalRowNum;

    /**
     * 每页条数
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPageNum;

    /**
     * 当前页数
     */
    private int pageIndex;
}
