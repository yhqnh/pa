package com.ikamobile.pa.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zhangcheng on 2016/8/3.
 */
@Slf4j
@Component
public class SystemInfo {

    private static String env;

    public static boolean isProd(){
        return "prod".equals(env);
    }

    @Value("${env}")
    public void setEnv(String env) {
        SystemInfo.env = env;
        log.info("-----env="+env);
    }
}
