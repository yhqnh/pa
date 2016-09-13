package com.ikamobile.pa.service;

import com.ikamobile.pa.dao.model.Driver;
import com.ikamobile.pa.dao.model.Vehicle;
import com.ikamobile.pa.thrift.server.acceptor.DriverDto;
import com.ikamobile.pa.thrift.server.acceptor.VehicleDto;

/**
 * Created by zhangcheng on 2016/7/13.
 */
public interface VehicleAndDriverService {

    /**
     * 获取车辆信息
     * @param id
     * @return
     */
    Vehicle getVehicleById(String id);

    /**
     * 通过自编号获取车辆
     * @param code
     * @return
     */
    Vehicle getVehicleByCode(String code);

    /**
     * 获取车辆信息
     * @param id
     * @return
     */
    VehicleDto getVehicleDtoById(String id);

    /**
     * 获取司机信息
     * @param id
     * @return
     */
    Driver getDriverById(String id);

    /**
     * 获取司机信息
     * @param id
     * @return
     */
    DriverDto getDriverDtoById(String id);




}
