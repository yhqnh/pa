package com.ikamobile.pa.dispatchapi.Interceptor.handler;

import com.ikamobile.pa.dispatchapi.response.BaseResponse;
import com.ikamobile.pa.dispatchapi.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/7.
 */
@Slf4j
public class UnknownExceptionHandler implements ExceptionHandler {
    @Override
    public BaseResponse handle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException{
        ErrorResponse errorResponse = new ErrorResponse();
        return errorResponse;
    }
}
