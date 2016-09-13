package com.ikamobile.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ikamobile.service.SMSVendor;
import com.ikamobile.service.exception.SMSException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 */
@Service
@Slf4j
public class YunPianSMSVendorImpl implements SMSVendor {
    @Value("${jms.pa.sms.apikey}")
    private String apikey;
    @Value("${jms.pa.sms.vendor.url}")
    private String vendorUrl;


    @Override
    public void send(String to, String msg) throws SMSException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(vendorUrl);
        List<NameValuePair> nvps = new ArrayList<>();
        try {
            nvps.add(new BasicNameValuePair("apikey", apikey));
            nvps.add(new BasicNameValuePair("mobile", to));

            nvps.add(new BasicNameValuePair("text", msg));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            SMSException smsException = new SMSException(SMSException.INTERNAL_ERROR, "严重错误：" + e.getMessage());
            smsException.record(to, msg);
            throw smsException;
        }

        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String json = EntityUtils.toString(entity);
                log.info("return json;{}",json);
                JSONObject jsonObject = JSONObject.parseObject(json);
                Integer code = (Integer) jsonObject.get("code");
                if (0 == code) {
                    log.info("短信发送成功 {}:{}", to, msg);
                } else {
                    log.info("yunpian sms send failed {}:{}", to, msg);
                    String errmsg = (String) jsonObject.get("detail");
                    if(org.springframework.util.StringUtils.isEmpty(errmsg)){
                        errmsg="unknown";
                    }
                    String errorCode = String.valueOf(jsonObject.get("code"));
                    if(org.springframework.util.StringUtils.isEmpty(errorCode)){
                        errorCode = "10";
                    }
                    log.info("error--code:{},message:{}", errorCode, errmsg);
                    new RuntimeException(errmsg);
                    SMSException smsException = new SMSException(errorCode,errmsg);
                    smsException.record(to, msg);
                    throw smsException;
                }
            }
            response.close();
        } catch (IOException e) {
            SMSException smsException = new SMSException(SMSException.INTERNAL_ERROR, "严重错误:" + e.getMessage());
            smsException.record(to, msg);
            throw smsException;
        }finally {
            httpPost.releaseConnection();
        }

    }
}
