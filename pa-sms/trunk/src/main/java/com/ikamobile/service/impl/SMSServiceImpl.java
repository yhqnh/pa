package com.ikamobile.service.impl;

import com.ikamobile.service.SMSService;
import com.ikamobile.service.SMSVendor;
import com.ikamobile.service.exception.SMSException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/7/12.
 */
@Service
@Slf4j
public class SMSServiceImpl implements SMSService {
    @Autowired
    private SMSVendor yunpianVendor;

    @Value("${env}")
    private String env;

    @Override
    public void send(String type, String msg, String to) throws SMSException {
        log.info("短信发送请求：vendorId={};to={};msg={}",type,to, msg);
        try {
            if("prod".equals(env))
            {
                yunpianVendor.send(to,msg);
                log.info("短信发送成功：to=" + to + " ;msg=" + msg);
            } else {
                log.info("测试环境不发短信：to="+to+" ;msg="+msg);
            }
        } catch (SMSException e) {
            log.warn("短信发送失败：to=" + to + " ;msg=" + msg);
            throw e;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
