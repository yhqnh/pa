package com.ikamobile.service;

import com.ikamobile.Application;
import com.ikamobile.dao.SMSRecordService;
import com.ikamobile.service.exception.SMSException;
import com.ikamobile.pa.message.service.jms.entity.AccountMessage;
import com.ikamobile.pa.message.service.jms.entity.OrderMessage;
import com.ikamobile.pa.message.service.jms.entity.VerificationCodeMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Administrator on 2016/7/14.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
public class PaSmsServiceTest {
    @Autowired
    private PaSmsService paSmsService;

    @Autowired
    private SMSVendor smsVendor;

    @Autowired
    private SMSRecordService smsRecordService;

    @Test
    public void testOnValidationCode() throws Exception {
        VerificationCodeMessage verificationCodeMessage= new VerificationCodeMessage();
        verificationCodeMessage.setMobile("18328540795");
        verificationCodeMessage.setVerificationCode("saff");
        System.out.println("====================="+verificationCodeMessage);
        paSmsService.sendVerifyCode(verificationCodeMessage);

        smsVendor.send("18328540795","qqqq");


    }

    @Test
    public void testOnConfirmOrder() throws Exception {
        OrderMessage confirmOrderMessage= new OrderMessage();
        confirmOrderMessage.setMobile("1111");

        confirmOrderMessage.setArrivePlace("成都");
        confirmOrderMessage.setArriveTime(new Date());
        confirmOrderMessage.setDriverMobile("110");
        confirmOrderMessage.setDriverName("王大头");
        System.out.println("====================="+confirmOrderMessage);
        paSmsService.orderStartNotify(confirmOrderMessage);


        smsVendor.send("18328540795","qqqq");


    }

    @Test
    public void testOnAddVehicleSend() throws Exception {
        AccountMessage driverAccountMessage= new AccountMessage();
        driverAccountMessage.setMobile("18328540795");

        driverAccountMessage.setAccount("1126769181");
        driverAccountMessage.setPassword("69181");

        System.out.println("====================="+driverAccountMessage);
        paSmsService.sendAccount(driverAccountMessage);

        smsVendor.send("18328540795","qqqq");
    }

    @Test
    public void testEx() throws SMSException {
        try {
            String errorCode = "8";
            String errmsg = null;
            SMSException smsException = new SMSException(errorCode, errmsg);
            String to = "12312312313";
            String msg = "asdfasj";
            smsException.record(to, msg);
            throw smsException;
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testFormat() throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat();
        f.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date arriveTime = f.parse("2016-02-04 14:05:51");
        SimpleDateFormat formatter = new SimpleDateFormat();
        formatter.applyPattern("M月d日H:mm");

        String time=formatter.format(arriveTime);
        System.out.println(time);
    }
}