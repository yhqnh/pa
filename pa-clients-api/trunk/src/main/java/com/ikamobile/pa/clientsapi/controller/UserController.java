package com.ikamobile.pa.clientsapi.controller;

import com.ikamobile.pa.clientsapi.common.util.ControllerUtil;
import com.ikamobile.pa.clientsapi.controller.result.User;
import com.ikamobile.pa.clientsapi.enums.VerityCodeEnum;
import com.ikamobile.pa.clientsapi.response.NotLoginResponse;
import com.ikamobile.pa.clientsapi.response.SimpleResponse;
import com.ikamobile.pa.thrift.client.UserServiceClientProxy;
import com.ikamobile.pa.thrift.client.VerityCodeServiceClientProxy;
import com.ikamobile.pa.thrift.common.OperateResponse;
import com.ikamobile.pa.thrift.server.acceptor.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 订单接口
 * Created by yanghuqianghq on 2016/7/01.
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {

    @Resource
    UserServiceClientProxy userScp;

    @Resource
    VerityCodeServiceClientProxy verityCodeScp;

    /**
     * 预定人登陆
     *
     * @param phoneNumber 手机号
     * @param verifyCode  验证码
     * @return 用户id
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse<User> login(@NotEmpty String phoneNumber, @NotEmpty String verifyCode) throws TException {
        SimpleResponse simpleResponse = new SimpleResponse();
//        Subject currentUser = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(phoneNumber, phoneNumber);
//        token.setRememberMe(true);
//        currentUser.login(token);
//
//        OperateResponse operateResponse = verityCodeScp.createProxy().isValid(Long.parseLong(phoneNumber), VerityCodeEnum.REGISTER.name(), verifyCode);
//        simpleResponse.setCode(operateResponse.getOperateCode().getValue());
//        simpleResponse.setMessage(operateResponse.getMessage());
        UserDto userDto = userScp.createProxy().login(phoneNumber, verifyCode);
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        simpleResponse.setData(user);
        return simpleResponse;
    }

    /**
     * 登陆
     */
    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse notLogin(HttpServletRequest request, HttpServletResponse response) throws TException {
        log.info("notLogin");
        SimpleResponse simpleResponse = new SimpleResponse();
        NotLoginResponse notLoginResponse = new NotLoginResponse();
        simpleResponse.setData(ControllerUtil.getSessionId(request));
        simpleResponse.setCode(notLoginResponse.getCode());
        simpleResponse.setMessage(notLoginResponse.getMessage());
        return simpleResponse;
    }
}
