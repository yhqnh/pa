package com.ikamobile.pa.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/17.
 */

/**
 * 这里可以做在线人数统计之类的业务
 */
@Slf4j
public class PaSessionListener extends SessionListenerAdapter{

    @Override
    public void onExpiration(Session session) {
        super.onExpiration(session);
        log.info("session{} is expire",session);
    }

    @Override
    public void onStart(Session session) {
        super.onStart(session);
        log.info("session{} is start",session);
    }
}
