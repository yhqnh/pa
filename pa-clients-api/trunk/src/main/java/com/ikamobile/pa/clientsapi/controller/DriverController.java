package com.ikamobile.pa.clientsapi.controller;

import com.ikamobile.pa.clientsapi.controller.result.Driver;
import com.ikamobile.pa.clientsapi.controller.result.User;
import com.ikamobile.pa.clientsapi.enums.VerityCodeEnum;
import com.ikamobile.pa.clientsapi.response.SimpleResponse;
import com.ikamobile.pa.thrift.client.DriverServiceClientProxy;
import com.ikamobile.pa.thrift.client.VerityCodeServiceClientProxy;
import com.ikamobile.pa.thrift.common.OperateResponse;
import com.ikamobile.pa.thrift.server.acceptor.DriverDto;
import com.ikamobile.pa.thrift.server.acceptor.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/7.
 */
@RestController
@RequestMapping("/driver")
@Slf4j
public class DriverController {

    @Resource
    VerityCodeServiceClientProxy verityCodeScp;

    @Resource
    DriverServiceClientProxy driverScp;

    /**
     * 司机登陆
     * @param phoneNumber 手机号
     * @param password 密码
     * @return 司机id
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse<Driver> login(@RequestParam String phoneNumber, @RequestParam String password) throws TException {
        SimpleResponse simpleResponse = new SimpleResponse();
//        Subject currentUser = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(phoneNumber, password);
//        token.setRememberMe(true);
//        currentUser.login(token);

        DriverDto driverDto = driverScp.createProxy().login(phoneNumber, password);
        Driver driver = new Driver();
        BeanUtils.copyProperties(driverDto, driver);
        simpleResponse.setData(driver);
        return simpleResponse;
    }
}
