package com.ikamobile.pa.wechat.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by zhangcheng on 2016/7/14.
 */
@Slf4j
@Controller
@RequestMapping("/weixin")
public class WechatController {

    @Value("${wechat.token}")
    private String TOKEN;

    @RequestMapping(method= RequestMethod.GET)
    @ResponseBody
    public String sign(HttpServletRequest request){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if(StringUtils.isNoneEmpty(signature,timestamp,nonce,echostr)) {
            log.info("---->{}-{}-{}-{}", signature, timestamp, nonce, echostr);
            if (checkSignature(signature, timestamp, nonce)) {
                return echostr;
            }
        }
        return "welcome!";
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public String handRequest(HttpServletRequest request){
        StringBuffer sb = new StringBuffer("");
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine())!=null){
                sb.append(line);
            }

            log.info("获取请求内容：{}",sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     *1）将token、timestamp、nonce三个参数进行字典序排序
     *2）将三个参数字符串拼接成一个字符串进行sha1加密
     *3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *@param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     *@param timestamp 时间戳
     *@param nonce 随机数
     *@return
     */
    private boolean checkSignature(String signature,String timestamp,String nonce){
        String[]arr=new String[]{TOKEN,timestamp,nonce};
        //1、排序
        Arrays.sort(arr);
        //2、生成字符串
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<arr.length;i++){
            sb.append(arr[i]);
        }
        //sha1加密
        log.info("---->string {}",sb.toString());
        String temp = DigestUtils.sha1Hex(sb.toString());
        log.info("---->sha1hex {}",temp);
        return signature.equals(temp);
    }

}

