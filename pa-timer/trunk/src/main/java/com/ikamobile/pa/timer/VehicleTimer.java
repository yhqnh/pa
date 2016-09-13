package com.ikamobile.pa.timer;


import com.ikamobile.pa.thrift.client.VehicleTrafficServiceClientProxy;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/8/29.
 */
@Controller
@Slf4j
public class VehicleTimer {

    @Autowired
    private VehicleTrafficServiceClientProxy vehicleTrafficScp;

//    @Scheduled(initialDelay = 1000, fixedDelay = 6000 * 10)
    @Scheduled(cron = "0 1 0 * * ?") //凌晨1点开始
    public void refreshTraffic() {
        log.info("===refreshTraffic...start===");
        try {
            vehicleTrafficScp.createProxy().refreshTraffic();
        } catch (TException te) {
            log.info("根据限行规则设置车辆状态错误", te);
        }
        log.info("===refreshTraffic...end===");
    }
}
