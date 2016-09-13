package com.ikamobile.pa.dispatchapi.Interceptor.handler;

import com.ikamobile.pa.dispatchapi.response.BaseResponse;
import com.ikamobile.pa.dispatchapi.response.ConstraintsResponse;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhangcheng on 2016/8/2.
 */
public class MissParamHandler implements ExceptionHandler {
    @Override
    public BaseResponse handle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
        String message = "请求报错";
        if(ex instanceof MissingServletRequestParameterException){
            MissingServletRequestParameterException e = (MissingServletRequestParameterException) ex;
            message = "缺少参数:"+e.getParameterName();
        }else if(ex instanceof MissingPathVariableException){
            MissingPathVariableException e = (MissingPathVariableException) ex;
            message = "缺少参数:"+e.getVariableName();
        }
        BaseResponse res = new ConstraintsResponse();
        res.setMessage(message);
        return res;
    }
}
