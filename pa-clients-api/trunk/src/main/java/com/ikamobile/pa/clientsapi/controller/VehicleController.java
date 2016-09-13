package com.ikamobile.pa.clientsapi.controller;

import com.ikamobile.pa.thrift.client.UserServiceClientProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单接口
 * Created by yanghuqianghq on 2016/7/01.
 */
@RestController
@RequestMapping("/vehicle")
@Slf4j
public class VehicleController {

    @Resource
    UserServiceClientProxy userServiceClientProxy;


}
