package com.ikamobile.pa.service;

import com.ikamobile.pa.common.utils.PasswordHelper;
import com.ikamobile.pa.dao.DispatcherDao;
import com.ikamobile.pa.dao.model.Dispatcher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangcheng on 2016/7/12.
 */

public class DispatcherServiceTest extends AbstractServiceTest {

    @Autowired
    DispatcherService dispatcherService;

    @Autowired
    DispatcherDao dispatcherDao;

    @Autowired
    private PasswordHelper passwordHelper;


//    @Test
    public void testRegister() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setLoginName("demo");
        String salt = passwordHelper.genSalt();
        dispatcher.setSalt(salt);
        dispatcher.setPassword(passwordHelper.encryptPassword("demo", salt));
        dispatcher.setPhoneNumber("15680783289");

        dispatcherDao.insertSelective(dispatcher);
    }

}