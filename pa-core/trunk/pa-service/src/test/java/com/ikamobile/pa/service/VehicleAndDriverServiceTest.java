package com.ikamobile.pa.service;

import com.ikamobile.pa.dao.model.Vehicle;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by zhangcheng on 2016/7/21.
 */
public class VehicleAndDriverServiceTest  extends AbstractServiceTest{

    @Resource
    private VehicleAndDriverService vehicleAndDriverService;
    @Test
    public void testGetVehicleById() throws Exception {
        Vehicle vehicleById = vehicleAndDriverService.getVehicleById("005acd32-5b1c-495d-80d3-f4a1395cad98");
        Vehicle vehicleById2 = vehicleAndDriverService.getVehicleById("10e9ddaa-3f39-4643-995b-cfffec5a8c85");
        System.out.println(vehicleById);
    }

    @Test
    public void testGetDriverById() throws Exception {
        vehicleAndDriverService.getDriverById("097b0f70-0c83-41ca-af10-a0cab4fb7005");
        vehicleAndDriverService.getDriverById("0f5000ab-618d-4b4b-9253-7bca33fabacc");

    }
}