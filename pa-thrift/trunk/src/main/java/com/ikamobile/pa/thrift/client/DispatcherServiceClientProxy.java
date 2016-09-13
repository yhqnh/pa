package com.ikamobile.pa.thrift.client;

import com.ikamobile.pa.thrift.server.acceptor.DispatcherThriftService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/6/21.
 */
@Slf4j
public class DispatcherServiceClientProxy implements MethodInterceptor {

    private static final String SERVER_NAME = "DispatcherThriftService";

    private OpenThriftClient openThriftClient;

    //要代理的原始对象
    private ThreadLocal<DispatcherThriftService.Client> client = new ThreadLocal<>();

    private ThreadLocal<TTransport> transport = new ThreadLocal<>();

    @Override
    public Object intercept(Object proxy, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        String superName = methodProxy.getSuperName();
        boolean needIntercept = true;
        if (superName.contains("CGLIB$send_")) {
            needIntercept = false;
        }
        if (superName.contains("CGLIB$recv_")) {
            needIntercept = false;
        }

        if (superName.contains("CGLIB$sendBase")) {
            needIntercept = false;
        }

        if (superName.contains("CGLIB$receiveBase")) {
            needIntercept = false;
        }

        try {
            if (needIntercept) {
                doBefore();
            }
            //调用原始对象方法
            result = methodProxy.invokeSuper(proxy, params);
        } catch (TApplicationException e) {
            if (e.getType() == TApplicationException.MISSING_RESULT) {
                return null;
            }
        } finally {
            if (needIntercept) {
                doAfter();
            }
        }
        return result;
    }

    public void doAfter() {
//        if (this.transport.get().isOpen()) {
//            this.transport.get().close();
//        }
        openThriftClient.getClient().close();
    }

    public void doBefore() {
        try {
            if (!this.transport.get().isOpen()) {
                this.transport.get().open();
            }
        } catch (Exception ste) {
            log.info("关闭transport发生异常", ste);
        }
    }

    public DispatcherThriftService.Client createProxy() {
        Enhancer enhancer = new Enhancer();
        try {
            TTransport tTransport = openThriftClient.getClient().getTransport();
            TProtocol tProtocol = openThriftClient.getClient().getProtocol(tTransport, SERVER_NAME);
            this.transport.set(tTransport);
            enhancer.setSuperclass(DispatcherThriftService.Client.class);// 设置代理目标
            enhancer.setCallback(this);// 设置回调
            Class[] classes = {TProtocol.class};
            TProtocol[] object = {tProtocol};
            return (DispatcherThriftService.Client) enhancer.create(classes, object);
        } catch (Exception e) {
            log.error("初始化GeneralThriftAcceptorProxy代理失败", e);
            throw new RuntimeException("获取thrift client失败");
        }
    }

    public OpenThriftClient getOpenThriftClient() {
        return openThriftClient;
    }

    public void setOpenThriftClient(OpenThriftClient openThriftClient) {
        this.openThriftClient = openThriftClient;
    }
}
