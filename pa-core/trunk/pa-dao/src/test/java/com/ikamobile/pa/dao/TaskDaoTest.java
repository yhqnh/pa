package com.ikamobile.pa.dao;

import com.alibaba.fastjson.JSON;
import com.ikamobile.pa.dao.model.Task;
import com.ikamobile.pa.dao.query.Criteria;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.util.JavaScriptUtils;

import java.util.List;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/4.
 */
@Slf4j
public class TaskDaoTest extends AbstractTest {

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private TaskDao taskDao;

//    @Test
    public void testQuery() {
        CriteriaQuery query = new CriteriaQuery();
        Criteria criteria = query.createCriteria();
        query.clear();
        criteria = query.createCriteria();
        criteria.andEqualTo(Task.FIELD_VEHICLE_ID, "f0664b22-4afa-4f94-9036-d5b4acf6f3f2");
        criteria.andNotEqualTo(Task.FIELD_STATUS, Task.TaskStatus.finish.getValue());
        List<Task> taskList = taskDao.selectByCriteriaQuery(query);
        if (!StringUtils.isEmpty(taskList) && taskList.size() > 0) {
            log.info(JSON.toJSONString(taskList));
        }
    }
}
