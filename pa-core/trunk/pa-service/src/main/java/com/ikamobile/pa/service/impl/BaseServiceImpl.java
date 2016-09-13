package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.dao.TaskDao;
import com.ikamobile.pa.dao.VehicleDao;
import com.ikamobile.pa.dao.model.Dispatcher;
import com.ikamobile.pa.dao.model.Driver;
import com.ikamobile.pa.dao.model.Task;
import com.ikamobile.pa.dao.model.Vehicle;
import com.ikamobile.pa.dao.query.Criteria;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.dao.query.Pager;
import com.ikamobile.pa.thrift.common.PagerInfoDto;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/1.
 */
@Slf4j
@Service
@Transactional(rollbackFor = {RuntimeException.class, TBusinessException.class})
public class BaseServiceImpl {

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private TaskDao taskDao;

    public void convert(Pager pager, PagerInfoDto pagerInfoDto) {
        if (StringUtils.isEmpty(pager)) {
            return;
        }
        pagerInfoDto.setPageIndex(pager.getPageIndex());
        pagerInfoDto.setPageSize(pager.getPageSize());
        int totalPageNum = (pager.getTotalSzie() + pager.getPageSize() - 1) / pager.getPageSize();
        pagerInfoDto.setTotalPageNum(totalPageNum);
        pagerInfoDto.setTotalRowNum(pager.getTotalSzie());
    }

    public void convert(List<Vehicle> vehicleList, List<VehicleDto> vehicleDtoList) {
        if (StringUtils.isEmpty(vehicleList) || vehicleList.size() < 0) {
            return;
        }
        for (Vehicle vehicle : vehicleList) {
            VehicleDto vehicleDto = new VehicleDto();
            convert(vehicle, vehicleDto);
            vehicleDtoList.add(vehicleDto);
        }
    }

    public void convert(Vehicle vehicle, VehicleDto vehicleDto) {
        if (StringUtils.isEmpty(vehicle)) {
            return;
        }
        if (StringUtils.isEmpty(vehicle.getSeats())) {
            vehicle.setSeats(0);
        }
        BeanUtils.copyProperties(vehicle, vehicleDto);
        CriteriaQuery query = new CriteriaQuery();
        Criteria criteria = query.createCriteria();
        criteria.andEqualTo(Task.FIELD_VEHICLE_ID, vehicle.getId());
        criteria.andEqualTo(Task.FIELD_STATUS, Task.TaskStatus.ready.getValue());
        List<Task> taskList = taskDao.selectByCriteriaQuery(query);
        if(!StringUtils.isEmpty(taskList) && taskList.size() > 0){
            vehicleDto.setTokenCount(taskList.get(0).getPsgCount());
        } else {
            vehicleDto.setTokenCount(0);
        }

        if(!StringUtils.isEmpty(vehicle.getType())){
            vehicleDto.setType(VehicleType.valueOf(vehicle.getType()));
        }
        if(!StringUtils.isEmpty(vehicle.getStatus())){
            vehicleDto.setStatus(VehicleStatus.valueOf(vehicle.getStatus()));
        }
    }

    public void convert(Driver driver, DriverDto driverDto) {
        if (StringUtils.isEmpty(driver)) {
            return;
        }
        if (StringUtils.isEmpty(driver.getAge())) {
            driver.setAge(0);
        }
        BeanUtils.copyProperties(driver, driverDto);
    }

    public void convert(Dispatcher dispatcher, DispatcherDto dispatcherDto) {
        if (StringUtils.isEmpty(dispatcher)) {
            return;
        }
        BeanUtils.copyProperties(dispatcher, dispatcherDto);
    }
}
