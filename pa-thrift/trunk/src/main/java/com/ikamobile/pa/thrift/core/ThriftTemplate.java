package com.ikamobile.pa.thrift.core;

import com.ikamobile.ikasoa.core.STException;
import com.ikamobile.pa.thrift.client.OpenThriftClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/8/16.
 */
@Slf4j
public class ThriftTemplate {

    private OpenThriftClient openThriftClient;

    public OpenThriftClient getOpenThriftClient() {
        return openThriftClient;
    }

    public void setOpenThriftClient(OpenThriftClient openThriftClient) {
        this.openThriftClient = openThriftClient;
    }

    public Object execute(ThriftCallback callback) throws TException {
        try {
            return callback.doInThrift(openThriftClient.getClient());
        } finally {
            openThriftClient.getClient().close();
        }
    }
}
