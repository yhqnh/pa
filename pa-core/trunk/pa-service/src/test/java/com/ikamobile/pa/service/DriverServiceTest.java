package com.ikamobile.pa.service;

import com.ikamobile.pa.common.utils.PasswordHelper;
import com.ikamobile.pa.dao.DriverDao;
import com.ikamobile.pa.service.impl.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangcheng on 2016/7/12.
 */

public class DriverServiceTest extends AbstractServiceTest{

    @Autowired
    DriverDao driverDao;

    @Autowired
    DriverServiceImpl driverService;

    @Autowired
    private PasswordHelper passwordHelper;

//    @Test
    public void testRegister(){
//        List<Driver> driverList = driverDao.selectByCriteriaQuery(null);
//        for (Driver driver : driverList) {
//            System.out.println(driver.getName());

//            driver.setLoginName(driver.getPhoneNumber());
//            String salt = passwordHelper.genSalt();
//            driver.setSalt(salt);
//            driver.setPassword(passwordHelper.encryptPassword(driver.getPhoneNumber().substring(5,11),salt));
//            driver.setId(UUID.randomUUID().toString());

//            UpdateByCriteriaQueryParam query = new UpdateByCriteriaQueryParam();
//            query.createCriteria().andEqualTo(VerifyCode.FIELD_PHONE_NUMBER, driver.getPhoneNumber());
//            driver.setId(UUID.randomUUID().toString());
//            query.setTarget(driver);
//            driverDao.updateByCriteriaQuery(query);
//        }
    }
}