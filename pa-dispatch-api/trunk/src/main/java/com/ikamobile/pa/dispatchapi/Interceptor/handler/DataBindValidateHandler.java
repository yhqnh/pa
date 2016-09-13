package com.ikamobile.pa.dispatchapi.Interceptor.handler;

import com.ikamobile.pa.dispatchapi.response.BaseResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhangcheng on 2016/8/2.
 */
public class DataBindValidateHandler implements ExceptionHandler {
    @Override
    public BaseResponse handle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
        BindException be = (BindException) ex;
        List<ObjectError> errors = be.getAllErrors();
        StringBuffer message = new StringBuffer("");
        if(errors!=null&&errors.size()>0){
            for (ObjectError error:errors){
                if(error instanceof FieldError){
                    message.append(((FieldError)error).getField()).append(error.getDefaultMessage());
                }else{
                    message.append(error.getObjectName()).append(error.getDefaultMessage());
                }
            }
        }
        BaseResponse baseResponse = new BaseResponse(200,message.toString());
        return baseResponse;
    }
}
