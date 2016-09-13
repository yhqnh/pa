package com.ikamobile.service;

import com.ikamobile.service.exception.SMSException;

/**
 * Created by Administrator on 2016/7/10.
 */
public interface SMSService {
    void send(String type,String msg,String to) throws SMSException;
}
