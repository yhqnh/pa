package com.ikamobile.pa.clientsapi.controller;

import com.ikamobile.pa.clientsapi.enums.VerityCodeEnum;
import com.ikamobile.pa.clientsapi.response.SimpleResponse;
import com.ikamobile.pa.thrift.client.VerityCodeServiceClientProxy;
import com.ikamobile.pa.thrift.common.OperateResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 订单接口
 * Created by yanghuqianghq on 2016/7/01.
 */
@Controller
@RequestMapping("/verityCode")
@Slf4j
@Validated
public class VerityCodeController {

    @Resource
    VerityCodeServiceClientProxy verityCodeScp;

    /**
     * 预定人获取下单验证码
     *
//     * @param phoneNumber 手机号
     * @return
     */
    @RequestMapping(value = "/getCreateOrder", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse getCreateOrder(@NotEmpty String phoneNumber) throws TException {
        SimpleResponse simpleResponse = new SimpleResponse();
        OperateResponse operateResponse = verityCodeScp.createProxy().get(phoneNumber, VerityCodeEnum.CREATE_ORDER.name());
        simpleResponse.setCode(operateResponse.getOperateCode().getValue());
        simpleResponse.setMessage(operateResponse.getMessage());
        return simpleResponse;
    }

    /**
     * 预定人获取注册验证码
     *
     * @param phoneNumber 手机号
     * @return
     */
    @RequestMapping(value = "/getRegister", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse getRegister(@NotEmpty String phoneNumber) throws TException {
        SimpleResponse simpleResponse = new SimpleResponse();
        OperateResponse operateResponse = verityCodeScp.createProxy().get(phoneNumber, VerityCodeEnum.REGISTER.name());
        simpleResponse.setCode(operateResponse.getOperateCode().getValue());
        simpleResponse.setMessage(operateResponse.getMessage());
        return simpleResponse;
    }
}
