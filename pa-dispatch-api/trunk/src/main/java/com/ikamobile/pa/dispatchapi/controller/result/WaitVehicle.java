package com.ikamobile.pa.dispatchapi.controller.result;

import lombok.Data;

/**
 * Created by zhangcheng on 2016/7/8.
 */
@Data
public class WaitVehicle extends Vehicle{

    private Driver driver;

    private int tokenCount;

}
