package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.dao.DriverDao;
import com.ikamobile.pa.dao.TaskDao;
import com.ikamobile.pa.dao.VehicleDao;
import com.ikamobile.pa.dao.model.Driver;
import com.ikamobile.pa.dao.model.Task;
import com.ikamobile.pa.dao.model.Vehicle;
import com.ikamobile.pa.dao.param.UpdateByIdParam;
import com.ikamobile.pa.dao.query.Criteria;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.dao.query.Pager;
import com.ikamobile.pa.service.VehicleService;
import com.ikamobile.pa.thrift.common.OperateResponse;
import com.ikamobile.pa.thrift.common.PagerDto;
import com.ikamobile.pa.thrift.common.PagerInfoDto;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
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
public class VehicleServiceImpl extends BaseServiceImpl implements VehicleService {

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private TaskDao taskDao;

    @Override
    public OperateResponse update(VehicleUpdateParam param) throws TBusinessException, TException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(param.getId());
        if(!StringUtils.isEmpty(param.getType())){
            vehicle.setType(param.getType().name());
        }
        if(!StringUtils.isEmpty(param.getStatus())){
            vehicle.setStatus(param.getStatus().name());
        }
        vehicleDao.updateByIdSelective(new UpdateByIdParam(param.getId(),vehicle));
        return new OperateResponse();
    }

    @Override
    public List<VehicleDto> findWaitSendList(long expectBoardTime) throws TBusinessException, TException {

        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(expectBoardTime), ZoneId.systemDefault());

        CriteriaQuery query = new CriteriaQuery();
        Criteria criteria = query.createCriteria();
        query.addSort(Vehicle.FIELD_CODE, CriteriaQuery.Sort.Direction.ASC);
        //取消状态条件，因为可能你排的任务可能不在今天，而数据保存的是车辆今日的限行信息
        criteria.andEqualTo(Vehicle.FIELD_TYPE, VehicleType.TRANSPORT.name());
        List<Vehicle> vehicleList = vehicleDao.selectByCriteriaQuery(query);
        //过滤限行预计上传日期车辆
        List<Vehicle> filterVehicleList = new ArrayList<>();
        for (Vehicle vehicle : vehicleList){
            if(com.ikamobile.pa.common.utils.StringUtils.isLimit(vehicle.getNumber(),localDateTime.toLocalDate())){
                continue;
            }
            if(!"ENABLE".equals(vehicle.getStatus())){
                vehicle.setStatus("ENABLE");
            }
            filterVehicleList.add(vehicle);
        }


        List<VehicleDto> vehicleDtoList = new ArrayList<>();
        for (Vehicle vehicle : filterVehicleList) {
            //过滤已经分配任务的车辆
            query.clear();
            criteria = query.createCriteria();
            criteria.andEqualTo(Task.FIELD_VEHICLE_ID,vehicle.getId());
            criteria.andEqualTo(Task.FIELD_STATUS, Task.TaskStatus.carry.getValue());
            List<Task> taskList = taskDao.selectByCriteriaQuery(query);
            if(!StringUtils.isEmpty(taskList) && taskList.size() > 0){
                continue;
            }

            VehicleDto vehicleDto = new VehicleDto();
            convert(vehicle, vehicleDto);
            Driver driver = driverDao.selectById(vehicle.getDriverId());
            DriverDto driverDto = new DriverDto();
            convert(driver, driverDto);
            vehicleDto.setDriverDto(driverDto);
            vehicleDtoList.add(vehicleDto);
        }
        return vehicleDtoList;
    }

    @Override
    public VehiclePagerDto getAllList(PagerDto pagerDto) throws TBusinessException, TException {
        VehiclePagerDto vehiclePagerDto = new VehiclePagerDto();
        //排序
        CriteriaQuery query = new CriteriaQuery();
        query.addSort(Vehicle.FIELD_CODE, CriteriaQuery.Sort.Direction.ASC);
        //过滤条件
        Criteria criteria = query.createCriteria();
        criteria.andEqualTo(Vehicle.FIELD_TYPE, VehicleType.TRANSPORT.name());
        Long totalSize = vehicleDao.countByCriteriaQuery(query);
        //分页
        Pager pager = new Pager(pagerDto.getPageIndex(), pagerDto.getPageSize());
        query.setPager(pager);
        List<Vehicle> vehicleList = vehicleDao.selectByCriteriaQuery(query);

        PagerInfoDto pagerInfoDto = new PagerInfoDto();
        pager.setTotalSzie(totalSize.intValue());
        convert(pager, pagerInfoDto);
        List<VehicleDto> vehicleDtoList = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            VehicleDto vehicleDto = new VehicleDto();
            convert(vehicle, vehicleDto);

            Driver driver = driverDao.selectById(vehicle.getDriverId());
            DriverDto driverDto = new DriverDto();
            convert(driver, driverDto);
            vehicleDto.setDriverDto(driverDto);
            vehicleDtoList.add(vehicleDto);
        }

        vehiclePagerDto.setPagerInfoDto(pagerInfoDto);
        vehiclePagerDto.setVehicleDtoList(vehicleDtoList);
        return vehiclePagerDto;
    }

    @Override
    public VehiclePagerDto getFilterList(PagerDto pagerDto, FilterDto filterDto) throws TBusinessException, TException {
        VehiclePagerDto vehiclePagerDto = new VehiclePagerDto();
        //排序
        CriteriaQuery query = new CriteriaQuery();
        query.addSort(Vehicle.FIELD_CODE, CriteriaQuery.Sort.Direction.ASC);
        //过滤
        Criteria criteria = query.createCriteria();
        if(!StringUtils.isEmpty(filterDto.getType())){
            criteria.andEqualTo(Vehicle.FIELD_TYPE, filterDto.getType().name());
        }
        if(!StringUtils.isEmpty(filterDto.getStatus())){
            criteria.andEqualTo(Vehicle.FIELD_STATUS, filterDto.getStatus().name());
        }
        if(!StringUtils.isEmpty(filterDto.getCode())){
            criteria.andEqualTo(Vehicle.FIELD_CODE, filterDto.getCode());
        }
        Long totalSize = vehicleDao.countByCriteriaQuery(query);
        //分页
        Pager pager = new Pager(pagerDto.getPageIndex(), pagerDto.getPageSize());
        query.setPager(pager);
        List<Vehicle> vehicleList = vehicleDao.selectByCriteriaQuery(query);

        PagerInfoDto pagerInfoDto = new PagerInfoDto();
        pager.setTotalSzie(totalSize.intValue());
        convert(pager, pagerInfoDto);
        List<VehicleDto> vehicleDtoList = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            VehicleDto vehicleDto = new VehicleDto();
            convert(vehicle, vehicleDto);

            Driver driver = driverDao.selectById(vehicle.getDriverId());
            DriverDto driverDto = new DriverDto();
            convert(driver, driverDto);
            vehicleDto.setDriverDto(driverDto);
            vehicleDtoList.add(vehicleDto);
        }

        vehiclePagerDto.setPagerInfoDto(pagerInfoDto);
        vehiclePagerDto.setVehicleDtoList(vehicleDtoList);
        return vehiclePagerDto;
    }
}
