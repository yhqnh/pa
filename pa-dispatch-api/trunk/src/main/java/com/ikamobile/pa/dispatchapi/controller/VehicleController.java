package com.ikamobile.pa.dispatchapi.controller;

import com.ikamobile.pa.dispatchapi.controller.param.PageParam;
import com.ikamobile.pa.dispatchapi.controller.param.VehicleQueryParam;
import com.ikamobile.pa.dispatchapi.controller.param.VehicleUpdateParam;
import com.ikamobile.pa.dispatchapi.controller.result.Driver;
import com.ikamobile.pa.dispatchapi.controller.result.VehicleDetail;
import com.ikamobile.pa.dispatchapi.controller.result.WaitVehicle;
import com.ikamobile.pa.dispatchapi.response.PageContent;
import com.ikamobile.pa.dispatchapi.response.SimpleResponse;
import com.ikamobile.pa.thrift.client.UserServiceClientProxy;
import com.ikamobile.pa.thrift.client.VehicleServiceClientProxy;
import com.ikamobile.pa.thrift.common.OperateResponse;
import com.ikamobile.pa.thrift.common.PagerDto;
import com.ikamobile.pa.thrift.core.ThriftTemplate;
import com.ikamobile.pa.thrift.server.acceptor.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单接口
 * Created by yanghuqianghq on 2016/7/01.
 */
@RestController
@RequestMapping("/vehicle")
@Slf4j
public class VehicleController extends BaseController{

    @Resource
    UserServiceClientProxy userScp;

    @Resource
    VehicleServiceClientProxy vehicleScp;

    @Resource
    ThriftTemplate thriftTemplate;

    /**
     * 获取待派车列表
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/getWaitSendList", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse<WaitVehicle> findWaitSendList(Long expectBoardTime)  throws TException {
        if(expectBoardTime == null){
            expectBoardTime = System.currentTimeMillis();
        }
        SimpleResponse simpleResponse = new SimpleResponse();
        List<VehicleDto> vehicleDtoList = vehicleScp.createProxy().findWaitSendList(expectBoardTime);

        List<WaitVehicle> waitVehicleList = new ArrayList<>();
        for (VehicleDto vehicleDto : vehicleDtoList) {
            WaitVehicle waitVehicle = new WaitVehicle();
            BeanUtils.copyProperties(vehicleDto,waitVehicle);

            Driver driver = new Driver();
            BeanUtils.copyProperties(vehicleDto.getDriverDto(),driver);
            waitVehicle.setDriver(driver);
            waitVehicleList.add(waitVehicle);
        }
        simpleResponse.setData(waitVehicleList);
        return simpleResponse;
    }

    /**
     * 获取所有车辆列表
     * @param param
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/getAllList", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse<PageContent<List<VehicleDetail>>> getAllList(@ModelAttribute PageParam param) throws TException {
        SimpleResponse simpleResponse = new SimpleResponse();
        PagerDto pagerDto = new PagerDto();
        pagerDto.setPageIndex(param.getPageIndex());
        pagerDto.setPageSize(param.getPageSize());

        VehiclePagerDto vehiclePagerDto = vehicleScp.createProxy().getAllList(pagerDto);
        List<VehicleDetail> vehicleDetailList = new ArrayList<>();
        for (VehicleDto vehicleDto : vehiclePagerDto.getVehicleDtoList()) {
            VehicleDetail vehicleDetail = new VehicleDetail();
            BeanUtils.copyProperties(vehicleDto, vehicleDetail);

            Driver driver = new Driver();
            BeanUtils.copyProperties(vehicleDto.getDriverDto(), driver);
            vehicleDetail.setDriver(driver);
            vehicleDetailList.add(vehicleDetail);
        }

        PageContent pageContent = new PageContent();
        covert(vehiclePagerDto.getPagerInfoDto(), pageContent);
        pageContent.setPageContent(vehicleDetailList);
        simpleResponse.setData(pageContent);
        return simpleResponse;
    }

    /**
     * 根据过滤条件获取车辆列表
     * @param param
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/getFilterList", method = RequestMethod.GET)
    @ResponseBody
    public SimpleResponse<PageContent<List<VehicleDetail>>> getFilterList(@ModelAttribute VehicleQueryParam param)  throws TException {
        SimpleResponse simpleResponse = new SimpleResponse();
        PagerDto pagerDto = new PagerDto();
        pagerDto.setPageIndex(param.getPageIndex());
        pagerDto.setPageSize(param.getPageSize());

        FilterDto filterDto = new FilterDto();
        if(!StringUtils.isEmpty(param.getType())){
            filterDto.setType(VehicleType.valueOf(param.getType()));
        }
        if(!StringUtils.isEmpty(param.getStatus())){
            filterDto.setStatus(VehicleStatus.valueOf(param.getStatus()));
        }
        filterDto.setCode(param.getCode());

        VehiclePagerDto vehiclePagerDto = (VehiclePagerDto) thriftTemplate.execute(new ThriftCallback() {
            @Override
            public Object doInThrift(ThriftClient client) throws TException {
                Object result = null;
                try {
                    result = new VehicleThriftService.Client(client.getProtocol(client.getTransport(),"VehicleThriftService")).getFilterList(pagerDto, filterDto);
                } finally {
                    return result;
                }
            }
        });
//        VehiclePagerDto vehiclePagerDto = vehicleScp.createProxy().getFilterList(pagerDto,filterDto);
        List<VehicleDetail> vehicleDetailList = new ArrayList<>();
        for (VehicleDto vehicleDto : vehiclePagerDto.getVehicleDtoList()) {
            VehicleDetail vehicleDetail = new VehicleDetail();
            BeanUtils.copyProperties(vehicleDto,vehicleDetail);

            Driver driver = new Driver();
            BeanUtils.copyProperties(vehicleDto.getDriverDto(),driver);
            vehicleDetail.setDriver(driver);
            vehicleDetailList.add(vehicleDetail);
        }

        PageContent pageContent = new PageContent();
        covert(vehiclePagerDto.getPagerInfoDto(), pageContent);
        pageContent.setPageContent(vehicleDetailList);
        simpleResponse.setData(pageContent);
        return simpleResponse;
    }

    /**
     * 更新车辆的类型
     * @param param
     * @return
     * @throws TException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse update(@ModelAttribute VehicleUpdateParam param)  throws TException {
        SimpleResponse simpleResponse = new SimpleResponse();

        com.ikamobile.pa.thrift.server.acceptor.VehicleUpdateParam vehicleUpdateParam = new com.ikamobile.pa.thrift.server.acceptor.VehicleUpdateParam();
        BeanUtils.copyProperties(param,vehicleUpdateParam);
        if(!StringUtils.isEmpty(param.getStatus())){
            vehicleUpdateParam.setStatus(VehicleStatus.valueOf(param.getStatus()));
        }
        if(!StringUtils.isEmpty(param.getType())){
            vehicleUpdateParam.setType(VehicleType.valueOf(param.getType()));
        }

        OperateResponse operateResponse = vehicleScp.createProxy().update(vehicleUpdateParam);
        simpleResponse.setCode(operateResponse.getOperateCode().getValue());
        simpleResponse.setMessage(operateResponse.getMessage());
        return simpleResponse;
    }
}
