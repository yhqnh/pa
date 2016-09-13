package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.common.enums.OperateLogTargetType;
import com.ikamobile.pa.common.utils.DateTimeUtils;
import com.ikamobile.pa.common.utils.ListUtils;
import com.ikamobile.pa.common.utils.StringUtils;
import com.ikamobile.pa.dao.OrderDao;
import com.ikamobile.pa.dao.TaskDao;
import com.ikamobile.pa.dao.model.Order;
import com.ikamobile.pa.dao.model.Task;
import com.ikamobile.pa.dao.param.UpdateByIdParam;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.service.OperateLogService;
import com.ikamobile.pa.service.ScheduledOrderService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by guest on 16/8/2.
 */
@Service
@Slf4j
@Transactional
public class ScheduledOrderServiceImpl implements ScheduledOrderService {

    @Resource
    private OperateLogService operateLogService;
    @Resource
    private OrderDao orderDao;
    @Resource
    private TaskDao taskDao;

    @Override
    @Scheduled(cron = "0 0/5 * * * ?")
    public void catchExOrder() {

        log.info("异常订单定时任务==============>begin");
        //查询所有异常订单
        //1,起飞时间小于当前时间+20分钟
        //2,状态不为【已送达,未接异常，已取消，已拒绝】
        try {
            Date deadLineTime =new Date();//现在时间
            List<String> notInStatus = new ArrayList<String>(){{
                add("served");
                add("missed");
                add("cancelled");
                add("refused");
                add("exception");
            }};
            CriteriaQuery query = new CriteriaQuery();
            String nowTimeStr = DateTimeUtils.convertTimeToString(deadLineTime);
            log.info("服务器现在时间===>{}",nowTimeStr);
            query.or()
                    .andLessThanOrEqualTo(Order.FIELD_FLIGHT_DEP_TIME, nowTimeStr)
                    .andNotIn(Order.FIELD_STATUS,notInStatus);

            List<Order> orders = orderDao.selectByCriteriaQuery(query);
            Set<String> taskIds = new HashSet<>();

            if(!ListUtils.isNullOrEmpty(orders)){
                for(Order order : orders){
                    //更新订单
                    log.info("订单【id={}】已过飞机起飞时间还未结束，系统自动转入异常订单",order.getId());
                    Order updateTemp = new Order();
                    updateTemp.setStatus(Order.OrderStatus.exception);
                    orderDao.updateByIdSelective(new UpdateByIdParam(order.getId(),updateTemp));
                    taskIds.add(order.getTaskId());
                    //记录日志
                    operateLogService.orderFront(order.getId(),"飞机即将起飞，订单任务未完成，自动转为异常订单","系统定时任务");
                }
            }else {
                // do nothing
            }

            if(!taskIds.isEmpty()){
                log.info("异常订单所属任务:{}",taskIds);
                for (String taskId : taskIds) {
                    //做判空处理，有的订单的takId可能为空
                    if(StringUtils.isNullOrEmpty(taskId)){
                        continue;
                    }
                    query.clear();
                    query.or()
                            .andEqualTo(Order.FIELD_TASK_ID,taskId)
                            .andNotEqualTo(Order.FIELD_STATUS,Order.OrderStatus.exception);
                    List<Order> orders1 = orderDao.selectByCriteriaQuery(query);
                    log.info("任务{},非异常订单数量{}",taskId,orders1.size());
                    //如果任务下的所有订单都变成异常订单则结束任务
                    if(orders1==null || orders1.size()==0){
                        Task updateTemp = new Task();
                        updateTemp.setStatus(Task.TaskStatus.finish.getValue());
                        updateTemp.setUpdateTime(new Date());
                        log.info("任务{}设置为完成",taskId);
                        taskDao.updateByIdSelective(new UpdateByIdParam(taskId,updateTemp));
                        operateLogService.saveOperateLog(OperateLogTargetType.TASK,taskId,0,"订单全部异常，结束任务","系统定时任务");
                    }
                }
            }
        }catch (Exception e){
            log.error("ScheduledOrderServiceImpl->catchExOrder occurred some ex==>",e);
            throw e;
        }

        log.info("异常订单定时任务==============>end");

    }

    @Override
    @Scheduled(cron = "0 0/2 * * * ?")
    public void clearTimeOutOrderLock() {
        //查询带锁订单
        CriteriaQuery query = new CriteriaQuery();
        DateTime dateTime = new DateTime();
        Date targetTime = dateTime.minusMinutes(2).toDate();
        String targetTimeStr = DateTimeUtils.convertTimeToString(targetTime);
        query.or()
                .andIsNotNull(Order.FIELD_LOCK_KEY)
                .andLessThanOrEqualTo(Order.FIELD_UPDATE_TIME,targetTimeStr);
        List<Order> orders = orderDao.selectByCriteriaQuery(query);
        if(!ListUtils.isNullOrEmpty(orders)){
            for(Order order:orders){
                log.info("the order[id={}] clear the lock by the scheduled task",order.getId());
                clearLock(order);
            }
        }


    }

    private void clearLock(Order order){
        order.setLockKey(null);
        order.setUpdateTime(new Date());
        orderDao.updateById(new UpdateByIdParam(order.getId(), order));
    }


}
