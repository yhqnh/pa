package com.ikamobile.pa.dispatchapi.controller;

import com.ikamobile.pa.dispatchapi.controller.common.ControllerUtil;
import com.ikamobile.pa.dispatchapi.controller.result.Dispatcher;
import com.ikamobile.pa.dispatchapi.response.NotLoginResponse;
import com.ikamobile.pa.dispatchapi.response.SimpleResponse;
import com.ikamobile.pa.thrift.client.DispatcherServiceClientProxy;
import com.ikamobile.pa.thrift.client.UserServiceClientProxy;
import com.ikamobile.pa.thrift.server.acceptor.DispatcherDto;
import com.ikamobile.pa.thrift.server.acceptor.PaUserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.thrift.TException;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 调度
 * Created by yanghuqianghq on 2016/7/01.
 */
@RestController
@RequestMapping("/dispatcher")
@Slf4j
@Validated
public class DispatcherController {

    @Resource
    UserServiceClientProxy userScp;

    @Resource
    DispatcherServiceClientProxy dispatcherScp;


    /**
     * 未登陆
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

    /**
     * 调度登陆
     *
     * @param loginName 登录名
     * @param password  密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse<Dispatcher> login(@NotNull @RequestParam String loginName, @NotEmpty @RequestParam String password) throws TException {
        SimpleResponse simpleResponse = new SimpleResponse();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
//        token.setRememberMe(true);
        currentUser.login(token);

        PaUserDto paUserDto = (PaUserDto) currentUser.getPrincipal();
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setId(paUserDto.getId());
        dispatcher.setLoginName(paUserDto.getUsername());
        simpleResponse.setData(dispatcher);

        return simpleResponse;
    }

    /**
     * 调度登出
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse logout() throws TException {
        SimpleResponse simpleResponse = new SimpleResponse();
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return simpleResponse;
    }

    /**
     * 获取所有用户
     *
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse<List<Dispatcher>> getAll() throws TException {
        List<Dispatcher> dispatcherList = new ArrayList<>();
        SimpleResponse simpleResponse = new SimpleResponse();
        List<DispatcherDto> dispatcherDtoList = dispatcherScp.createProxy().getAll();
        for (DispatcherDto dispatcherDto : dispatcherDtoList) {
            Dispatcher dispatcher = new Dispatcher();
            BeanUtils.copyProperties(dispatcherDto, dispatcher);
            dispatcherList.add(dispatcher);
        }
        simpleResponse.setData(dispatcherList);
        return simpleResponse;
    }
}
