package com.ikamobile.pa.clientsapi.controller;

import com.ikamobile.pa.clientsapi.controller.result.TaskDetail;
import com.ikamobile.pa.thrift.server.acceptor.TaskDetailDto;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by zhangcheng on 2016/7/13.
 */
@Component
public class Transform {

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    private static Transform transform;
    @PostConstruct
    public void init(){
        transform = this;
        transform.dozerBeanMapper = this.dozerBeanMapper;
    }

    public static void transform(TaskDetailDto taskDetailDto,TaskDetail taskDetail) {
        transform.dozerBeanMapper.map(taskDetailDto,taskDetail);
    }
}
