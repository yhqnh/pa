package com.ikamobile.pa.wechat.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangcheng on 2016/7/14.
 */
@Slf4j
public class HttpUtil {


    public static String post(String url, Map<String,String> params, String urlEncode){


        HttpClient client=new DefaultHttpClient();
        HttpResponse response=null;
        HttpPost post=new HttpPost(url);
        List<NameValuePair> nvps=new ArrayList<NameValuePair>();
        for(String key:params.keySet())
        {
            nvps.add(new BasicNameValuePair(key,params.get(key)));
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, urlEncode));
            response=client.execute(post);
            HttpEntity entity = response.getEntity();
            String result= EntityUtils.toString(entity);
            return result;
        }catch (Exception e){

        }
        return null;
    }
    public static String post(String url,Map<String,String> params)
    {
        return  post(url,params,"UTF-8");

    }


    public static String get(String url,Map<String,String> params)
    {
        StringBuilder paramStr=new StringBuilder();
        try {
            for(String key:params.keySet())
            {
                String val = params.get(key);
                if(val!=null) {
                    paramStr.append("&").append(key).append("=").append(URLEncoder.encode(val, "UTF-8"));
                }
            }
            String reqUrl = url+paramStr;
            System.out.println("get 请求url:"+reqUrl);
            HttpGet httpGet=new HttpGet(reqUrl);
            HttpClient client=new DefaultHttpClient();
            HttpResponse response = client.execute(httpGet);
            log.debug("get请求response: "+response);
            HttpEntity entity = response.getEntity();
            String result=EntityUtils.toString(entity);
            log.debug("http get result:"+result);
            return result;
        }catch (Exception e){
            System.out.println("发生了一个请求错误");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)throws Exception
    {
        HttpGet httpGet=new HttpGet("http://dev.obt.slyi.cc/sme/order/notify/5/confirm.json?t=sBtyTFBm&token=1b97910f3209ef80a7de9fdc1c7095c5&TktCreateData=2015-11-04+20%3A45%3A03&ManCount=1&TicketNos=test%2Fchen%2C6656348952480%2CC1&OrderSource=YEEGO&OrderNo=9371138657750649&BalanceMoney=1105.0&IsHaveTkt=true&TktCount=1&PNR=ZZZZZZ&SubsOrderNo=9371138657750649&MsgType=DZ");
        HttpClient client=new DefaultHttpClient();
        HttpResponse response = client.execute(httpGet);
        System.out.println("get请求response: "+response);
        HttpEntity entity = response.getEntity();
        String result=EntityUtils.toString(entity);
        System.out.print("结果结果结果结果结果:" + result);
    }

}
