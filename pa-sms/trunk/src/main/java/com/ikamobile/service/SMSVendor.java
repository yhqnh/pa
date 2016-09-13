package com.ikamobile.service;

import com.ikamobile.service.exception.SMSException;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/7/12.
 */
public interface SMSVendor {
    String YPVENDOR = "1";
    void send(String to,String msg) throws SMSException, UnsupportedEncodingException;
}
