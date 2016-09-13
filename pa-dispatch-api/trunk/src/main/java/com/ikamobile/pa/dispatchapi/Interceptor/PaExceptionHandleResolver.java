package com.ikamobile.pa.dispatchapi.Interceptor;


import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.ikamobile.pa.dispatchapi.Interceptor.handler.*;
import com.ikamobile.pa.dispatchapi.response.BaseResponse;
import com.ikamobile.pa.thrift.common.TBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.core.Ordered;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统异常处理
 * Created by zhangcheng on 2016/7/21.
 */
@Slf4j
public class PaExceptionHandleResolver implements HandlerExceptionResolver,Ordered{

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");

            ExceptionHandler exceptionHandler = null;
            BaseResponse baseResponse = null;
            log.info("ex is {}", ex);
            if (ex instanceof TBusinessException) {
                exceptionHandler = new BussinessExceptionHandler();
            } else if (ex instanceof ShiroException) {//登陆权限异常
                exceptionHandler = new ShiroExceptionHandler();
            } else if (ex instanceof ConstraintViolationException) {//参数校验异常
                exceptionHandler = new  ConstraintsExceptionHandler();
            } else if(ex instanceof BindException){ //参数对象校验异常
                exceptionHandler = new DataBindValidateHandler();
            } else if(ex instanceof ServletRequestBindingException){ //请求路径缺少必要参数
                exceptionHandler = new MissParamHandler();
            } else if(ex instanceof HttpRequestMethodNotSupportedException){ //请求路径缺少必要参数
                exceptionHandler = new HttpRequestMethodNotSupportedExceptionHandler();
            } else {
                exceptionHandler = new UnknownExceptionHandler();
            }
            if(exceptionHandler!=null) {
                baseResponse = exceptionHandler.handle(request, response, handler, ex);
            }

            ModelAndView mv = new ModelAndView();
            FastJsonJsonView view = new FastJsonJsonView();
            Map<String, Object> attributes = new HashMap<String, Object>();
            if(baseResponse!=null) {
                attributes.put("code", baseResponse.getCode());
                attributes.put("message", baseResponse.getMessage());
            }
            view.setAttributesMap(attributes);
            mv.setView(view);
            return mv;
        } catch (Exception e) {

        }
        return null;
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
