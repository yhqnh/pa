package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.dao.DriverDao;
import com.ikamobile.pa.dao.TaskDao;
import com.ikamobile.pa.dao.VehicleDao;
import com.ikamobile.pa.dao.VehicleTrafficDao;
import com.ikamobile.pa.dao.model.Driver;
import com.ikamobile.pa.dao.model.Task;
import com.ikamobile.pa.dao.model.Vehicle;
import com.ikamobile.pa.dao.model.VehicleTraffic;
import com.ikamobile.pa.dao.param.UpdateByIdParam;
import com.ikamobile.pa.dao.query.Criteria;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.dao.query.Pager;
import com.ikamobile.pa.service.VehicleService;
import com.ikamobile.pa.service.VehicleTrafficService;
import com.ikamobile.pa.thrift.common.OperateResponse;
import com.ikamobile.pa.thrift.common.PagerDto;
import com.ikamobile.pa.thrift.common.PagerInfoDto;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/1.
 */
@Slf4j
@Service
@Transactional(rollbackFor = {RuntimeException.class, TBusinessException.class})
public class VehicleTrafficServiceImpl extends BaseServiceImpl implements VehicleTrafficService {

    @Autowired
    private VehicleTrafficDao vehicleTrafficDao;

    @Autowired
    private VehicleDao vehicleDao;

    public OperateResponse refreshTraffic() throws TException {
        OperateResponse operateResponse = new OperateResponse();
        CriteriaQuery query = new CriteriaQuery();
        Criteria criteria = query.createCriteria();
        criteria.andEqualTo(VehicleTraffic.FIELD_WEEK, DateTime.now().getDayOfWeek());
        VehicleTraffic vehicleTraffic = vehicleTrafficDao.selectOneByCriteriaQuery(query);
        if (!StringUtils.isEmpty(vehicleTraffic)) {
            //限行尾号
            String limitNumber = vehicleTraffic.getLimitNumber();
            //过滤条件
            List<Vehicle> vehicleList = vehicleDao.selectByCriteriaQuery(null);
            for (Vehicle vehicle : vehicleList) {
                String endNumber = vehicle.getNumber().charAt(vehicle.getNumber().length() - 1) + "";
                if (limitNumber.contains(endNumber)) {
                    vehicle.setStatus(VehicleStatus.DISABLE.name());
                } else {
                    vehicle.setStatus(VehicleStatus.ENABLE.name());
                }

                UpdateByIdParam param = new UpdateByIdParam(vehicle.getId(), vehicle);
                vehicleDao.updateById(param);
            }
        }
        return operateResponse;
    }
}
