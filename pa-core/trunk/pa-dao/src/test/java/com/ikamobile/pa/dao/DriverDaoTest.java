package com.ikamobile.pa.dao;

import com.alibaba.fastjson.JSON;
import com.ikamobile.pa.dao.model.Driver;
import com.ikamobile.pa.dao.model.Order;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/4.
 */
@Slf4j
public class DriverDaoTest extends AbstractTest{

    @Autowired
    private DriverDao driverDao;

//    @Test
    public void testSelectById() {

//        Driver driver = new Driver();
//        driver.setLoginName("test");
//        driver.setPhoneNumber("12345678900");
//        driverDao.insert(driver);
//
//        System.out.println(JSON.toJSONString(driverDao.selectById(driver.getId())));
//
//        driverDao.deleteById(driver.getId());

//        CriteriaQuery query = new CriteriaQuery();
//        query.or().andEqualTo(Driver.FIELD_PHONE_NUMBER,18980088093L);
//        Driver driver = driverDao.selectOneByCriteriaQuery(query);
//        System.out.println(driver);
    }
}
