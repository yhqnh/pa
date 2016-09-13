package com.ikamobile.pa.service;

import com.ikamobile.pa.dao.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AbstractContextLoader;

import static org.junit.Assert.*;

/**
 * Created by zhangcheng on 2016/7/12.
 */

public class TaskServiceTest extends AbstractServiceTest{

    @Autowired
    TaskService taskService;
    @Test
    public void testGetOrCreateByVehicle() throws Exception {

    }

    @Test
    public void testAssignOrderToVehicle() throws Exception {

    }

    @Test
    public void testRemoveFromTask() throws Exception {

    }

    @Test
    public void testRedis(){
        //taskService.putRedis("SSSSSSSd","WWWWWWWWWWWWWWWWWWWWWWWWd");

        //Task task = taskService.get("SSSSSSSd");

        //System.out.println(task);
    }
}