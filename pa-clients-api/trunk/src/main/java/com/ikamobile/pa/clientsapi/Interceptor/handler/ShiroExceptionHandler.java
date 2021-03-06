package com.ikamobile.pa.clientsapi.Interceptor.handler;

import com.alibaba.fastjson.JSON;
import com.ikamobile.pa.clientsapi.response.BaseResponse;
import com.ikamobile.pa.clientsapi.response.IncorrectCredentialsResponse;
import com.ikamobile.pa.clientsapi.response.InvalidSessionResponse;
import com.ikamobile.pa.clientsapi.response.ShiroResponse;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.session.InvalidSessionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/7.
 */
@Slf4j
public class ShiroExceptionHandler implements ExceptionHandler {

    @Override
    public BaseResponse handle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
        BaseResponse baseResponse;
        if(ex instanceof IncorrectCredentialsException){
            baseResponse = new IncorrectCredentialsResponse();
        } else if(ex instanceof InvalidSessionException){
            baseResponse = new InvalidSessionResponse();
        } else {
            baseResponse = new ShiroResponse();
            baseResponse.setMessage(ex.getMessage());
        }
        return  baseResponse;

    }
}
