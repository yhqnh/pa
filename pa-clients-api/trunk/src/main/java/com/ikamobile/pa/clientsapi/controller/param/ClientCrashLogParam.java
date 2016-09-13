package com.ikamobile.pa.clientsapi.controller.param;

import lombok.Data;

/**
 * Created by guest on 16/7/28.
 */
@Data
public class ClientCrashLogParam {
    //操作系统版本
    private String _OSVersion;
    //机器类型
    private String machineType;
    //crash日志
    private String crashLog;

}
