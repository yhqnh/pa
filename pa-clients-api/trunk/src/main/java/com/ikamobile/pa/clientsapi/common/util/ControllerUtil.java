package com.ikamobile.pa.clientsapi.common.util;

import org.springframework.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/17.
 */
public class ControllerUtil {

    private static String sessionName = "JSESSIONID";

    public static String getSessionId(HttpServletRequest request){
        boolean findCookie = false;
        Cookie[] cookies = request.getCookies();
        if(!org.springframework.util.StringUtils.isEmpty(cookies)){
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if(cookie.getName().equals(sessionName)){
                    findCookie = true;
                    return cookie.getValue();
                }
            }
        }
        if(!findCookie){
            return (String) request.getAttribute(sessionName);
        }
        return null;
    }
}
