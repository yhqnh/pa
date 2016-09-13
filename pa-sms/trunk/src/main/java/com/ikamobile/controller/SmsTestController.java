package com.ikamobile.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangcheng on 2016/8/3.
 */
@Controller
@RequestMapping("/sms")
@Slf4j
public class SmsTestController {
    @RequestMapping(value = "/single_send.json",method = RequestMethod.POST)
    @ResponseBody
    public String sendMessage(@RequestParam String apikey,@RequestParam String mobile,@RequestParam String text){
        log.info("发送短信：{}-{}",mobile,text);
        return "{code:0,detail:success}";
    }
}
