package com.ikamobile.pa.dispatchapi.Interceptor.handler;

import com.ikamobile.pa.dispatchapi.response.BaseResponse;
import com.ikamobile.pa.dispatchapi.response.ConstraintsResponse;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/7.
 */
@Slf4j
public class ConstraintsExceptionHandler implements ExceptionHandler {
    @Override
    public BaseResponse handle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException{
        ConstraintViolationException cvex = (ConstraintViolationException) ex;
        String bf = "参数异常：";
        for(ConstraintViolation violation : cvex.getConstraintViolations()) {
            bf += violation.getMessage();
            break;
        }
        ConstraintsResponse constraintsResponse = new ConstraintsResponse();
        constraintsResponse.setMessage(bf);
        //response.getWriter().write(JSON.toJSONString(constraintsResponse));
        return constraintsResponse;
    }
}
