package com.ikamobile.pa.dispatchapi.Interceptor.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/7.
 */
public class ExceptionContext {

    private ExceptionHandler handler;

    public ExceptionContext(ExceptionHandler handler) {
        this.handler = handler;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
        this.handler.handle(request, response, handler, ex);
    }
}
