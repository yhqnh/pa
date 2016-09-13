package com.ikamobile.pa.thrift.core;

import com.ikamobile.ikasoa.core.thrift.client.ThriftClient;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/8/16.
 */
public interface ThriftCallback {

    public Object doInThrift(ThriftClient client) throws TException;
}
