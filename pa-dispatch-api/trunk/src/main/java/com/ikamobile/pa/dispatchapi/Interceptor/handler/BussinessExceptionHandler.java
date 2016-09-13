package com.ikamobile.pa.dispatchapi.Interceptor.handler;

import com.ikamobile.pa.dispatchapi.response.BaseResponse;
import com.ikamobile.pa.thrift.common.TBusinessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/7.
 */
public class BussinessExceptionHandler implements ExceptionHandler{

    @Override
    public BaseResponse handle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException{
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(((TBusinessException) ex).getCode());
        baseResponse.setMessage(ex.getMessage());
        //response.getWriter().write(JSON.toJSONString(baseResponse));
        return baseResponse;
    }
}
