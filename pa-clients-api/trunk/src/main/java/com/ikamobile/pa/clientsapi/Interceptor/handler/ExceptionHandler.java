package com.ikamobile.pa.clientsapi.Interceptor.handler;

import com.ikamobile.pa.clientsapi.response.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/7.
 */
public interface ExceptionHandler {

    public BaseResponse handle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  throws IOException;
}
