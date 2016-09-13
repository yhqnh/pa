package com.ikamobile.pa.dispatchapi.controller;

import com.ikamobile.pa.dispatchapi.response.PageContent;
import com.ikamobile.pa.thrift.common.PagerDto;
import com.ikamobile.pa.thrift.common.PagerInfoDto;
import org.springframework.util.StringUtils;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/27.
 */
public class BaseController {

    protected void covert(PagerInfoDto pagerInfoDto, PageContent pageContent) {
        if(StringUtils.isEmpty(pagerInfoDto)){
            return;
        }
        pageContent.setPageIndex(pagerInfoDto.getPageIndex());
        pageContent.setPageSize(pagerInfoDto.getPageSize());
        pageContent.setTotalPageNum(pagerInfoDto.getTotalPageNum());
        pageContent.setTotalRowNum(pagerInfoDto.getTotalRowNum());
    }
}
