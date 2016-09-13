package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.dao.DriverDao;
import com.ikamobile.pa.dao.VehicleDao;
import com.ikamobile.pa.dao.model.Driver;
import com.ikamobile.pa.dao.model.Vehicle;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.service.VehicleAndDriverService;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.DriverDto;
import com.ikamobile.pa.thrift.server.acceptor.VehicleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangcheng on 2016/7/13.
 */
@Slf4j
@Service
@Transactional(rollbackFor = {RuntimeException.class, TBusinessException.class})
public class VehicleAndDriverServiceImpl extends BaseServiceImpl implements VehicleAndDriverService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private DriverDao driverDao;

    @Override
    @Cacheable(value = "Vehicle",key = "#id")
    public Vehicle getVehicleById(String id) {
        return vehicleDao.selectById(id);
    }

    /**
     * 通过自编号获取车辆
     *
     * @param code
     * @return
     */
    @Override
    public Vehicle getVehicleByCode(String code) {
        CriteriaQuery vehicleQuery = new CriteriaQuery();
        vehicleQuery.or().andEqualTo(Vehicle.FIELD_CODE, code);
        List<Vehicle> vehicles = vehicleDao.selectByCriteriaQuery(vehicleQuery);
        if(vehicles!=null && vehicles.size()>0){
            return vehicles.get(0);
        }
        return null;
    }

    @Override
    public VehicleDto getVehicleDtoById(String id) {
        Vehicle vehicle = getVehicleById(id);
        if(vehicle!=null) {
            VehicleDto vehicleDto = new VehicleDto();
            convert(vehicle, vehicleDto);
            return vehicleDto;
        }
        return null;
    }

    @Override
    @Cacheable(value = "Driver",key = "#id") //这个地方没做将json转对象的操作。报错
    public Driver getDriverById(String id) {
        return driverDao.selectById(id);
    }

    @Override
    public DriverDto getDriverDtoById(String id) {
        Driver driver =getDriverById(id);
        if(driver!=null) {
            DriverDto dto = new DriverDto();
            dto.setId(driver.getId());
            dto.setName(driver.getName());
            dto.setSex(driver.getSex());
            dto.setAge(driver.getAge()==null?0:driver.getAge());
            dto.setPhoneNumber(driver.getPhoneNumber());
            dto.setPhoneNumberOther(driver.getPhoneNumberOther());
            dto.setArea(driver.getArea());
            dto.setCityId(driver.getCityId());
            return dto;
        }
        return null;
    }
}
