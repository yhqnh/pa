package com.ikamobile.pa.dispatchapi.Interceptor.handler;

import com.alibaba.fastjson.JSON;
import com.ikamobile.pa.dispatchapi.response.BaseResponse;
import com.ikamobile.pa.dispatchapi.response.ConstraintsResponse;
import com.ikamobile.pa.dispatchapi.response.NotSupportedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/7.
 */
@Slf4j
public class HttpRequestMethodNotSupportedExceptionHandler implements ExceptionHandler {
    @Override
    public BaseResponse handle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException{
        HttpRequestMethodNotSupportedException httpNotSuportedEx = (HttpRequestMethodNotSupportedException) ex;

        NotSupportedResponse notSupportedResponse = new NotSupportedResponse();
        notSupportedResponse.setMessage(httpNotSuportedEx.getMethod() + "不支持");
        response.getWriter().write(JSON.toJSONString(notSupportedResponse));
        return notSupportedResponse;
    }
}
