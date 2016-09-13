package com.ikamobile.pa.clientsapi.Interceptor;

import com.alibaba.fastjson.JSON;
import com.ikamobile.pa.clientsapi.Interceptor.handler.*;
import com.ikamobile.pa.thrift.common.TBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.form.SelectTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/5.
 */
@Slf4j
public class ExceptionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (!StringUtils.isEmpty(ex)&&false) {
            ExceptionContext exceptionContext = null;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            log.info("ex is {}", ex);
            if (ex instanceof TBusinessException) {
                exceptionContext = new ExceptionContext(new BussinessExceptionHandler());
            } else if (ex instanceof ShiroException) {
                exceptionContext = new ExceptionContext(new ShiroExceptionHandler());
            } else if (ex instanceof ConstraintViolationException) {
                exceptionContext = new ExceptionContext(new ConstraintsExceptionHandler());
            } else {
                exceptionContext = new ExceptionContext(new UnknownExceptionHandler());
            }
            response.setStatus(HttpServletResponse.SC_OK);
            exceptionContext.handle(request, response, handler, ex);
        }
    }
}
