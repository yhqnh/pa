package com.ikamobile.pa.dao;

import com.alibaba.fastjson.JSON;
import com.ikamobile.pa.dao.model.Task;
import com.ikamobile.pa.dao.model.VehicleTraffic;
import com.ikamobile.pa.dao.query.Criteria;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/4.
 */
@Slf4j
public class VehicleTrafficDaoTest extends AbstractTest {

    @Autowired
    private VehicleTrafficDao vehicleTrafficDao;

//    @Test
    public void testQuery() {
        CriteriaQuery query = new CriteriaQuery();
        Criteria criteria = query.createCriteria();
        query.clear();
        criteria = query.createCriteria();
        criteria.andEqualTo(VehicleTraffic.FIELD_WEEK, "1");
        VehicleTraffic vehicleTraffic = vehicleTrafficDao.selectOneByCriteriaQuery(query);
        System.out.println(vehicleTraffic.getLimitNumber());

        vehicleTraffic.setCreateTime(new Date());
        vehicleTrafficDao.updateById(vehicleTraffic);

    }
}
