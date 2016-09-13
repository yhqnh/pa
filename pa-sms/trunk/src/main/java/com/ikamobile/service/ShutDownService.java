package com.ikamobile.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2016/7/11.
 */
@Service
@Slf4j
public class ShutDownService implements ApplicationContextAware,InitializingBean {
    private  ApplicationContext context;

    @Value("${sms.module.shutdown.port}")
    private int shutdownPort;

    private ServerSocket serverSocket;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        int i= 3;
        while (i-->0){
            try {
                serverSocket=new ServerSocket(shutdownPort);
                break;
            }catch (Exception e){
                log.error("服务启动失败，监听端口{}，剩余尝试次数{}",shutdownPort,i);
                Thread.sleep(3000);
            }
        }
        Thread shutdownThread= new Thread(new ShutdownListener());
        shutdownThread.start();
    }

    private class ShutdownListener implements Runnable {
        @Override
        public void run() {
            boolean always = true;
            while (always) {
                try {

                    log.info("Waiting for shutdown request .... listener port {} ", shutdownPort);

                    Socket socket = serverSocket.accept();

                    log.info("Accept shutdown request .... ");
                    log.info("Remote client ip (shutdown): {}", socket.getRemoteSocketAddress());
                    InputStream is = socket.getInputStream();
                    DataInputStream dis = new DataInputStream(is);

                    int stopFlag = dis.readInt();
                    log.info("Read shutdown flag is {} ", stopFlag);

                    if (stopFlag == Integer.MAX_VALUE) {
                        always = false;

                        log.info("Right, going to shutdown ...");
                        log.info("Shutdown applicationContext");
                        if (context instanceof Closeable) {
                            ((Closeable) context).close();
                            log.info("Shutdown applicationContext success ... ");
                            serverSocket.close();
                            break;
                        } else {
                            log.info("Shutdown applicationContext fail ... ");
                        }
                    } else {
                        log.info("Wrong stop flag!!!  ignore that ... ");
                    }

                } catch (IOException e) {
                    log.warn("catch error ... ", e);
                    if(always)
                        continue;
                    else
                        break;
                }
            }
            System.exit(0);
        }
    }
}
