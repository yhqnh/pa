package com.ikamobile.pa.clientsapi.controller;

import com.ikamobile.pa.clientsapi.common.util.StringUtils;
import com.ikamobile.pa.clientsapi.controller.handler.OrderDetailHandler;
import com.ikamobile.pa.clientsapi.controller.param.OrderCreateParam;
import com.ikamobile.pa.clientsapi.controller.result.OrderDetail;
import com.ikamobile.pa.clientsapi.controller.result.Passenger;
import com.ikamobile.pa.clientsapi.enums.CertificateTypeEnum;
import com.ikamobile.pa.clientsapi.response.BaseResponse;
import com.ikamobile.pa.clientsapi.response.SimpleResponse;
import com.ikamobile.pa.thrift.client.OrderServiceClientProxy;
import com.ikamobile.pa.thrift.client.VerityCodeServiceClientProxy;
import com.ikamobile.pa.thrift.common.OperateCode;
import com.ikamobile.pa.thrift.common.OperateResponse;
import com.ikamobile.pa.thrift.common.Sorter;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.dozer.DozerBeanMapper;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单接口
 * Created by yanghuqianghq on 2016/7/01.
 */
@RestController
@RequestMapping("/pa/order")
@Slf4j
@Validated
public class OrderController {

    @Resource
    OrderServiceClientProxy orderServiceClientProxy;

    @Resource
    VerityCodeServiceClientProxy verityCodeServiceClientProxy;

    @Resource
    DozerBeanMapper dozerBeanMapper;

    @Value("${customer.service.hot.line}")
    private  String  customerServiceHotLine;


    /**
     * 新增订单
     * @param param 创建参数 请将参数以json的格式放在body
     * @return 订单详情
     */
    @RequestMapping(value = "/")
    public SimpleResponse createOrderAction(@RequestBody @Validated OrderCreateParam param ){
        //校验起飞时间是否合法
        SimpleResponse<String> valiResponse = valiFlightDepTimeAction(param.getFlightDepTime());
        if(valiResponse.getCode() != 0){
            return new SimpleResponse(1,valiResponse.getMessage());
        }

        //数据转换
        SimpleResponse<OrderDetail> response = new SimpleResponse<>();
        try {
            //校验验证码是否正确
            OperateResponse operateResponse = verityCodeServiceClientProxy.createProxy().isValid(param.getMobile(), "CREATE_ORDER", param.getVerificationCode());
            if(!OperateCode.success.equals(operateResponse.operateCode)){
                throw new TBusinessException(1,"验证码不正确");
            }

            com.ikamobile.pa.thrift.server.acceptor.OrderCreateParam thriftParam  = new com.ikamobile.pa.thrift.server.acceptor.OrderCreateParam();
            dozerBeanMapper.map(param,thriftParam);
            OrderDetailDto orderDetailDto = orderServiceClientProxy.createProxy().create(thriftParam);
            OrderDetail orderDetail = convertToOrderDetail(orderDetailDto);
            response.setCode(0);
            response.setData(orderDetail);
        } catch (TBusinessException e) {
            log.error("create order  occurred some bex",e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (TException e){
            //系统异常
            log.error("create order  occurred some sys ex",e);
            response.setCode(500);
            response.setMessage("系统异常");
        }

        return response;

    }

    /**
     * 校验起飞时间，是否能够进行预订，规则如下：
     * 06：00~13:00 时间段，不包含13：00
     * 需要提前一天的22：00前完成预约。
     * 13：00~23：00 时间段不包含23：00
     * 需要提前4个小时完成预约。
     * @param flightDepTimeStamp 起飞时间戳
     * @return 订单详情
     */
    @RequestMapping(value = "/valiFlightDepTime")
    public SimpleResponse<String> valiFlightDepTimeAction(@NotNull Long flightDepTimeStamp){

        SimpleResponse<String> response = new SimpleResponse<>();

        Instant instant = Instant.ofEpochMilli(flightDepTimeStamp);
        LocalDateTime flightDepTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        if(flightDepTime.isBefore(LocalDateTime.now())){
            response.setCode(1);
            response.setMessage("航班起飞时间不能早于当前，详询请拨打客服热线:<a href=\"tel:"+customerServiceHotLine+"\">"+customerServiceHotLine+"</a>");
            response.setData(customerServiceHotLine);
            return response;
        }

        if(flightDepTime.getHour() >= 6 && flightDepTime.getHour() <13){
            LocalDateTime endTime = LocalDateTime.of(flightDepTime.toLocalDate().minusDays(1), LocalTime.of(22, 00));
            if(endTime.isBefore(LocalDateTime.now())){
                response.setCode(1);
                response.setMessage("");
                response.setMessage("四川航空温馨提示：06:00~13:00时间段起飞的航班，需要提前一天的22：00前完成约车，您已超过预订时间。如需预约请拨打：<a href=\"tel:"+customerServiceHotLine+"\">"+customerServiceHotLine+"</a>");
            }

        } else if(flightDepTime.getHour() >= 13 && flightDepTime.getHour() <23){

            if(LocalDateTime.now().plusHours(4).isAfter(flightDepTime)){
                response.setCode(1);
                response.setMessage("四川航空温馨提示：13:00~23:00时间段起飞的航班，需要提前4小时完成约车，您已超过预订时间。如需预约请拨打：<a href=\"tel:"+customerServiceHotLine+"\">"+customerServiceHotLine+"</a>");
            }
        }
        if(response.getCode() != 0){
            response.setData(customerServiceHotLine);
        }
        return response;
    }



    /**
     * 取消订单
     * @param orderId 订单id
     * @return SimpleResponse
     */
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    public BaseResponse cancelOrderAction(@NotBlank String orderId,@NotBlank String mobile){
        BaseResponse response = new BaseResponse();
        try {
            OperateResponse operateResponse = orderServiceClientProxy.createProxy().cancelOrder(orderId, mobile);
            response.setCode(operateResponse.getOperateCode().getValue());
            response.setMessage(operateResponse.getMessage());
        } catch (TBusinessException e) {
            log.error("cancel order  [id={}] occurred some ex",orderId,e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (TException e){
            //系统异常
            log.error("cancel order  occurred some sys ex",e);
            response.setCode(500);
            response.setMessage("系统异常");
        }
        return response;
    }


    /**
     * 订单详情
     * @param id 订单id
     * @param mobile 预订人手机号
     * @return SimpleResponse
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public SimpleResponse<OrderDetail> orderDetailAction(@PathVariable String id,String mobile){
        SimpleResponse<OrderDetail> response = new SimpleResponse<>();
        response.setCode(0);
        try {
            OrderDetailDto dto = orderServiceClientProxy.createProxy().orderDetail(id);
            if(dto == null){
                throw new TBusinessException(1,"订单不存在");
            }
            OrderDetail orderDetail = convertToOrderDetail(dto);
            response.setData(orderDetail);
        } catch (TBusinessException e) {
            log.error("get order Detail [id={}] occurred some ex",id,e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (Exception e){
            //系统异常
            log.error("get order Detail [id={}] occurred some sys ex",id,e);

            response.setCode(500);
            response.setMessage("系统异常");
        }
        return response;
    }

    /**
     * 订单列表
     * @param mobile 预订人手机号
     * @return SimpleResponse 数据为订单列表
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public SimpleResponse<List<OrderDetail>> orderListAction(@NotBlank String mobile){
        SimpleResponse<List<OrderDetail>> response = new SimpleResponse<>();

        OrderQueryParam orderQueryPram = new OrderQueryParam();
        orderQueryPram.setSorter(new OrderQuerySorterEnum());//默认以创建时间逆排序
        orderQueryPram.getSorter().setCreateTime(Sorter.DESC);
        orderQueryPram.setSearchParam(new OrderSearchParam());
        orderQueryPram.getSearchParam().setBookerMobile(mobile);

        try {
            OrderPageResponse pageResponse = orderServiceClientProxy.createProxy().listOrder(orderQueryPram);
            List<OrderDetailDto> pageContent = pageResponse.getPageContent();
            List<OrderDetail> orderDetails = new ArrayList<>();
            if(pageContent != null){
                for(OrderDetailDto dto : pageContent){
                    OrderDetail orderDetail = convertToOrderDetail(dto);
                    orderDetails.add(orderDetail);
                }
            }
            response.setData(orderDetails);
            response.setCode(0);
        } catch (TBusinessException e) {
            log.error("get order list [mobile={}] occurred some ex",mobile,e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (TException e){
            //系统异常
            response.setCode(500);
            response.setMessage("系统异常");
        }
        return response;
    }
    /**
     * 获取进行中的订单
     * @param mobile 预订人手机号
     * @return SimpleResponse 数据为订单详情
     */
    @RequestMapping(value = "/ing")
    public SimpleResponse<OrderDetail> getIngOrder(@NotBlank String mobile){

        SimpleResponse<OrderDetail> response = new SimpleResponse<>();
        OrderQueryParam orderQueryPram = new OrderQueryParam();
        orderQueryPram.setSearchParam(new OrderSearchParam());

        List<String> filterStatus = new ArrayList<String>(){{
            add("unconfirmed");
            add("confirmed");
            add("planned");
            add("waiting");
            add("picked");
        }};
        orderQueryPram.getSearchParam().setBookerMobile(mobile);
        orderQueryPram.getSearchParam().setStatus(filterStatus);
        orderQueryPram.setSorter(new OrderQuerySorterEnum());//默认以创建时间逆排序

        try {
            OrderPageResponse pageResponse = orderServiceClientProxy.createProxy().listOrder(orderQueryPram);
            List<OrderDetailDto> pageContent = pageResponse.getPageContent();
            OrderDetail orderDetail = null;
            if(pageContent != null && pageContent.size() > 0){
                orderDetail = convertToOrderDetail(pageContent.get(0));
            }
            response.setData(orderDetail);
            response.setCode(0);
        } catch (TBusinessException e) {
            log.error("get ing order [mobile={}] occurred some ex",mobile,e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (TException e){
            //系统异常
            response.setCode(500);
            response.setMessage("系统异常");
        }

        return response;
    }

    private OrderDetail convertToOrderDetail(OrderDetailDto orderDetailDto){
        OrderDetail map = dozerBeanMapper.map(orderDetailDto, OrderDetail.class);
        map.setStatusName(OrderDetailHandler.orderStatusMap.get(map.getStatus()));
        map.setExplaination(OrderDetailHandler.getExplainInfo(map));
        //证件处理
        for(Passenger p : map.getPassengers()){
            p.setCertificateTypeDesc(CertificateTypeEnum.valueOf(p.getCertificateType()).getDesc());
        }
        return map;
    }


}
