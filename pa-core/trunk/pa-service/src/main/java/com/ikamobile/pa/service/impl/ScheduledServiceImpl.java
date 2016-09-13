package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.common.utils.StringUtils;
import com.ikamobile.pa.dao.VehicleDao;
import com.ikamobile.pa.dao.model.Vehicle;
import com.ikamobile.pa.dao.param.UpdateByIdParam;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.service.ScheduledService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guest on 16/8/25.
 */
@Service
@Slf4j
@Transactional
public class ScheduledServiceImpl implements ScheduledService {
    @Resource
    private VehicleDao vehicleDao;

    @Override
    @Scheduled(cron = "0 0 3 * * ?")
    public void updateVehicleStatus() {
        // 查询所有司机
        List<Vehicle> vehicles = vehicleDao.selectByCriteriaQuery(new CriteriaQuery());
        for(Vehicle vehicle : vehicles){
            //如果今天限行
            try{
                if(StringUtils.isLimit(vehicle.getNumber(),null)){
                    if(!"DISABLE".equals(vehicle.getStatus())){
                        //  更新状态为DISABLE
                        vehicle.setStatus("DISABLE");
                        vehicleDao.updateByIdSelective(new UpdateByIdParam(vehicle.getId(),vehicle));
                        log.info("update the vehicle[number={}] to DISABLE by the scheduled Task",vehicle.getNumber());

                    }
                } else {
                    if(!"ENABLE".equals(vehicle.getStatus())){
                        //  更新状态为ENABLE
                        vehicle.setStatus("ENABLE");
                        vehicleDao.updateByIdSelective(new UpdateByIdParam(vehicle.getId(),vehicle));
                        log.info("update the vehicle[number={}] to ENABLE by the scheduled Task",vehicle.getNumber());
                    }
                }
            }catch (Exception e){
                log.error("when updateVehicleStatus by Scheduled task occurred some ex==>");

            }

        }

    }


}
