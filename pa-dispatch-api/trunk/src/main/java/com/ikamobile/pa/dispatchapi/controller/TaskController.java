package com.ikamobile.pa.dispatchapi.controller;

import com.ikamobile.pa.dispatchapi.controller.param.TaskListParam;
import com.ikamobile.pa.dispatchapi.controller.result.TaskDetail;
import com.ikamobile.pa.dispatchapi.controller.result.TaskListDetail;
import com.ikamobile.pa.dispatchapi.response.PageContent;
import com.ikamobile.pa.dispatchapi.response.SimpleResponse;
import com.ikamobile.pa.thrift.client.TaskServiceClientProxy;
import com.ikamobile.pa.thrift.common.OperateResponse;
import com.ikamobile.pa.thrift.common.PagerDto;
import com.ikamobile.pa.thrift.server.acceptor.TaskDetailDto;
import com.ikamobile.pa.thrift.server.acceptor.TaskListResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcheng on 2016/7/7.
 */
@RestController
@RequestMapping("/task")
@Slf4j
@Validated
public class TaskController {

    @Resource
    private TaskServiceClientProxy taskServiceClientProxy;

    @Resource
    private DozerBeanMapper dozerBeanMapper;

    /**
     * 获取任务详情
     * @param id 任务ID
     * @return 任务详情对象
     * @throws TException
     */
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public SimpleResponse<TaskDetail> getDetail(@NotNull @PathVariable String id)throws TException{
        TaskDetailDto dto = taskServiceClientProxy.createProxy().getTaskDetail(id);
        SimpleResponse<TaskDetail> response = new SimpleResponse<TaskDetail>();
        if(dto!=null){
//            log.info("getDetail 获取数据：{}", JSON.toJSONString(dto));
            TaskDetail detail = new TaskDetail();
            dozerBeanMapper.map(dto,detail);
//            log.info("getDetail 返回数据：{}", JSON.toJSONString(detail));
            response.setData(detail);
        }else{
            response.setCode(100);
            response.setMessage("找不到数据");
        }
        return response;
    }

    /**
     * 开始执行任务
     *
     * @param id 任务ID
     * @param operatorId 操作人员ID
     * @param updateTime 更新时间
     * @return 操作结果
     */
    @RequestMapping(value="/start/{id}",method = RequestMethod.GET)
    public SimpleResponse start(@NotNull @PathVariable String id,@NotNull  @RequestParam String operatorId,@NotNull  @RequestParam long updateTime) throws TException {
        OperateResponse response = taskServiceClientProxy.createProxy().startTask(id, operatorId,updateTime);
        if(response.getOperateCode().getValue()==0){
            return new SimpleResponse();
        }
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setCode(1);
        simpleResponse.setMessage(response.getMessage());
        return simpleResponse;
    }


    /**
     * 获取未开始的任务数量
     * @return
     */
    @RequestMapping(value="/pending/count",method = RequestMethod.GET)
    public SimpleResponse<Integer> pendingCount() throws TException {
        int pendingCount = taskServiceClientProxy.createProxy().getPendingCount();
        SimpleResponse<Integer> response = new SimpleResponse<Integer>();
        response.setData(pendingCount);
        return response;
    }

    /**
     * 获取任务列表
     * @param param 分页查询条件
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public SimpleResponse<PageContent<TaskListDetail>> list(TaskListParam param) throws TException {

        PagerDto params  = new PagerDto();
        params.setPageIndex(param.getPageIndex());
        params.setPageSize(param.getPageSize());
        TaskListResponse list = taskServiceClientProxy.createProxy().getPendingList(params,param.getOperatorId());
        List<TaskListDetail> details = new ArrayList<>();
        SimpleResponse<PageContent<TaskListDetail>> response = new SimpleResponse();
        PageContent<TaskListDetail> content = new PageContent<>();
        if(list == null||list.getData()==null||list.getData().size()==0){
            log.info("list 获取列表数据:无数据");
            response.setMessage("无数据");
            content.setPageContent(new ArrayList<>());
        }else {
//            log.info("list 获取列表数据：{}", JSON.toJSONString(list));
            dozerBeanMapper.map(list.getData(), details);
//            log.info("list 返回列表数据：{}", JSON.toJSONString(details));
            content.setPageContent(details);
        }

        if(list!=null&&list.getPager()!=null) {
            content.setPageIndex(list.getPager().getPageIndex());
            content.setPageSize(list.getPager().getPageSize());
            content.setTotalPageNum(list.getPager().getTotalPageNum());
            content.setTotalRowNum(list.getPager().getTotalRowNum());
        }
        response.setData(content);
        return response;
    }



}
