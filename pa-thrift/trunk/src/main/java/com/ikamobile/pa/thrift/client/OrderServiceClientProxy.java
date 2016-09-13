package com.ikamobile.pa.thrift.client;

import com.ikamobile.ikasoa.core.STException;
import com.ikamobile.pa.thrift.server.acceptor.ThriftOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TMessageType;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/6/21.
 */
@Slf4j
public class OrderServiceClientProxy implements MethodInterceptor {

    private static final String SERVER_NAME = "OrderService";

    private OpenThriftClient openThriftClient;

    //要代理的原始对象
    private ThriftOrderService$ThreadLocalClient client = null;

    private ThreadLocal<TTransport> transport = new ThreadLocal<>();

    @Override
    public Object intercept(Object proxy, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        Object result = null;


        try {
            doBefore();
            // 调用原始对象方法
            log.info("invokeSuper begin==>{}",method.getName());
            result = methodProxy.invokeSuper(proxy, params);
            log.info("invokeSuper end==>{}",method.getName());
        } catch (TApplicationException e) {
            if (e.getType() == TApplicationException.MISSING_RESULT) {
                return null;
            }
        } finally {
                doAfter();
        }
        return result;
    }

    public void doAfter() {
//        if (this.transport.get().isOpen()) {
//            this.transport.get().close();
//            log.debug("the transport is closed:{}",transport.get());
//        }
        log.info("doafter socket===>{}",((TSocket)transport.get()).getSocket());
        log.info("openThriftClient's internal thriftClient ===>{}",openThriftClient.getClient());
        openThriftClient.getClient().close();
    }

    public void doBefore() {
        try {
            if (!this.transport.get().isOpen()) {
                this.transport.get().open();
            }
        } catch (Exception ste) {
            log.info("开启transport发生异常", ste);
        }
    }

    public ThriftOrderService.Client createProxy() {

        if(client != null){
            try {
                TTransport tTransport = openThriftClient.getClient().getTransport();
                this.transport.set(tTransport);
                TProtocol tProtocol = openThriftClient.getClient().getProtocol(tTransport, SERVER_NAME);
                client.getIprot_s().set(tProtocol);
                client.getOprot_s().set(tProtocol);
                client.getSeqid_s().set(0);
                log.info("use a old client to====>{}",client.getClass().getName() + "@" + Integer.toHexString(client.hashCode()));
                return client;
            } catch (STException e) {
                log.error("获取thrift client失败", e);
                throw new RuntimeException("获取thrift client失败");
            }

        }

        Enhancer enhancer = new Enhancer();
        try {
            TTransport tTransport = openThriftClient.getClient().getTransport();
            TProtocol tProtocol = openThriftClient.getClient().getProtocol(tTransport, SERVER_NAME);
            log.debug("===>the transport===>{}", tTransport.toString());

            log.debug("===>the transport status===>{}", ((TSocket)tTransport).getSocket().isClosed());
            log.debug("===>the transport socket===>{}", ((TSocket)tTransport).getSocket());

            this.transport.set(tTransport);
            enhancer.setSuperclass(ThriftOrderService$ThreadLocalClient.class);// 设置代理目标
            Callback noOp=NoOp.INSTANCE;//(2)
            Callback[] callbacks = {this, noOp};
            enhancer.setCallbacks(callbacks);// 设置回调
            //设置代理毁掉过滤器，只有ThriftOrderService.Iface中声明的方法才调用该对象。
            enhancer.setCallbackFilter(new CallbackFilter() {
                @Override
                public int accept(Method method) {
                    try {
                        log.info("method==>{} can be  proxy?",method.getName());
                        ThriftOrderService.Iface.class.getMethod(method.getName(),method.getParameterTypes());
                        log.info("method==>{} accept proxy",method.getName());
                        return 0;
                    } catch (NoSuchMethodException e) {
                        return 1;
                    }
                }
            });
            Class[] classes = {TProtocol.class};
            TProtocol[] object = {tProtocol};
            ThriftOrderService$ThreadLocalClient client = (ThriftOrderService$ThreadLocalClient) enhancer.create(classes, object);
            System.out.println("create A order thriftService client===>"+client);
            this.client = client;
            return client;
        } catch (Exception e) {
            log.error("初始化GeneralThriftAcceptorProxy代理失败", e);
            throw new RuntimeException("获取thrift client失败");
        }
    }

    /**
     * 重写TServerClient类，使用ThreadLocal改造成员变量，使其可以使用单例模式，使得可以重复使用一个Client对象，而不是每次都去创建一个
     */
    private static class ThriftOrderService$ThreadLocalClient extends ThriftOrderService.Client{


        private ThreadLocal<TProtocol> iprot_s = new ThreadLocal<>();
        private ThreadLocal<TProtocol> oprot_s = new ThreadLocal<>();
        protected ThreadLocal<Integer> seqid_s = new ThreadLocal<>();

        public ThriftOrderService$ThreadLocalClient(TProtocol prot) {
            super(prot);
            iprot_s.set(prot);
            oprot_s.set(prot);
            seqid_s.set(0);
        }

        public ThreadLocal<TProtocol> getIprot_s() {
            return iprot_s;
        }

        public ThreadLocal<TProtocol> getOprot_s() {
            return oprot_s;
        }

        public ThreadLocal<Integer> getSeqid_s() {
            return seqid_s;
        }

        /**
         * Get the TProtocol being used as the input (read) protocol.
         * @return the TProtocol being used as the input (read) protocol.
         */
        public TProtocol getInputProtocol() {
            return iprot_s.get();
        }

        /**
         * Get the TProtocol being used as the output (write) protocol.
         * @return the TProtocol being used as the output (write) protocol.
         */
        public TProtocol getOutputProtocol() {
            return this.oprot_s.get();
        }

        protected void sendBase(String methodName, TBase<?,?> args) throws TException {
            sendBase(methodName, args, TMessageType.CALL);
        }

        protected void sendBaseOneway(String methodName, TBase<?,?> args) throws TException {
            sendBase(methodName, args, TMessageType.ONEWAY);
        }

        private void sendBase(String methodName, TBase<?,?> args, byte type) throws TException {
            seqid_s.set(seqid_s.get() + 1);
            oprot_s.get().writeMessageBegin(new TMessage(methodName, type, seqid_s.get()));
            args.write(oprot_s.get());
            oprot_s.get().writeMessageEnd();
            oprot_s.get().getTransport().flush();
        }

        protected void receiveBase(TBase<?,?> result, String methodName) throws TException {
            TMessage msg = iprot_s.get().readMessageBegin();
            if (msg.type == TMessageType.EXCEPTION) {
                TApplicationException x = TApplicationException.read(iprot_);
                iprot_s.get().readMessageEnd();
                throw x;
            }
            if (msg.seqid != seqid_s.get()) {
                throw new TApplicationException(TApplicationException.BAD_SEQUENCE_ID, methodName + " failed: out of sequence response");
            }
            result.read(iprot_s.get());
            iprot_s.get().readMessageEnd();
        }
    }



    public OpenThriftClient getOpenThriftClient() {
        return openThriftClient;
    }

    public void setOpenThriftClient(OpenThriftClient openThriftClient) {
        this.openThriftClient = openThriftClient;
    }
}
