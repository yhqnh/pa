package com.ikamobile.pa.clientsapi.controller.handler;

import com.ikamobile.pa.clientsapi.common.util.DateTimeUtils;
import com.ikamobile.pa.clientsapi.controller.result.OrderDetail;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guest on 16/7/14.
 */
public class OrderDetailHandler {

   public static Map<String,String> orderStatusMap = new HashMap(){{
       put("unconfirmed","待处理");
       put("confirmed","正在安排车辆");
       put("refused","已拒绝");
       put("planned","正在安排车辆");
       put("cancelled","已取消");
       put("waiting","已安排车辆");
       put("picked","已接到");
       put("missed","行程异常");
       put("served","已送达");
       put("exception","订单异常");
    }};

    public static Map<String,String> statusExplainMap = new HashMap(){{
        // todo 询问产品
        put("unconfirmed","已收到您的预约，请等候专享客服为您处理");
        put("confirmed","客服正在为您安排车辆，一会就好");
        put("refused","抱歉，您不符合免费送机条件（6折以上机票），您的预约已被拒绝");
        put("planned","客服正在为您安排车辆，一会就好");
        put("cancelled","您的预约已取消");
        put("waiting","您的送机专车预计将于%s到达上车地点");
        put("picked","司机已接到您，正在前往机场");
        put("missed","抱歉，发生了意外，司机未接到您");
        put("served","已抵达机场，祝您旅途愉快");
        put("exception","截止飞机起飞时间未送达");
    }};


    public static String getExplainInfo(OrderDetail orderDetail){

        if("waiting".equalsIgnoreCase(orderDetail.getStatus())){
            // todo 做是否是进判断
//            String time = "";
//            Long timeStamp = Long.parseLong(orderDetail.getFlightDepTime());
//            Date date = new Date(timeStamp);
//            date.getDate()
//            Calendar calendar = Calendar.getInstance();
            String dateStr = "时间未定";
            if(orderDetail.getExpectBoardTime() > 0){
                Date date = new Date(orderDetail.getExpectBoardTime());
                dateStr = DateTimeUtils.convertDateToString(date, DateTimeUtils.YYYY_MM_DD_HH_MM_FORMAT);
            }

            return String.format(statusExplainMap.get("waiting"), dateStr);
        } else {
            return statusExplainMap.get(orderDetail.getStatus());
        }

    }

    public static void main(String[] args) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus("waiting");
        orderDetail.setExpectBoardTime(new Date().getTime());
        System.out.println("===>"+getExplainInfo(orderDetail));
    }
}
