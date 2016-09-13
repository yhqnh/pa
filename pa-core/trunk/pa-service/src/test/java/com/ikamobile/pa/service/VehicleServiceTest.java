package com.ikamobile.pa.service;

import com.ikamobile.pa.common.utils.PasswordHelper;
import com.ikamobile.pa.dao.DriverDao;
import com.ikamobile.pa.dao.VehicleDao;
import com.ikamobile.pa.dao.model.Vehicle;
import com.ikamobile.pa.dao.param.UpdateByCriteriaQueryParam;
import com.ikamobile.pa.service.impl.DispatcherServiceImpl;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * Created by zhangcheng on 2016/7/12.
 */

public class VehicleServiceTest extends AbstractServiceTest {

    @Autowired
    DispatcherServiceImpl dispatcherService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleDao vehicleDao;
    
    @Autowired
    DriverDao driverDao;

    @Autowired
    private PasswordHelper passwordHelper;


//    @Test
    public void testRegister() {
        List<Vehicle> vehicleList = vehicleDao.selectByCriteriaQuery(null);
        for (Vehicle vehicle : vehicleList) {
            System.out.println(vehicle.getCode());

            UpdateByCriteriaQueryParam query = new UpdateByCriteriaQueryParam();
            query.createCriteria().andEqualTo(Vehicle.FIELD_CODE, vehicle.getCode());
            vehicle.setId(UUID.randomUUID().toString());
            query.setTarget(vehicle);
            vehicleDao.updateByCriteriaQuery(query);
        }
    }

//    @Test
    public void testUpdateType() throws TException{

    }

//    @Test
    public void testFindWaitSendList() throws TException {
        vehicleService.findWaitSendList(System.currentTimeMillis());
    }
}