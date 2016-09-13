package com.ikamobile.pa.clientsapi.Interceptor.handler;

import com.alibaba.fastjson.JSON;
import com.ikamobile.pa.clientsapi.response.BaseResponse;
import com.ikamobile.pa.clientsapi.response.ConstraintsResponse;
import com.ikamobile.pa.clientsapi.response.ErrorResponse;
import freemarker.template.utility.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.metadata.ConstraintDescriptor;
import java.io.IOException;
import java.util.Set;

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
