package com.ikamobile.pa.thrift.client;

import com.ikamobile.ikasoa.core.thrift.GeneralFactory;
import com.ikamobile.ikasoa.core.thrift.client.ThriftClient;
import com.ikamobile.ikasoa.core.thrift.client.ThriftClientConfiguration;
import org.apache.thrift.transport.TTransportFactory;

import java.util.UUID;

public class OpenThriftClient {

	private ThriftClient client;

	public OpenThriftClient(String serverHost, int serverPort) {
		ThriftClientConfiguration configuration = new ThriftClientConfiguration();
		configuration.setTransportFactory(new TTransportFactory());
		this.client = new GeneralFactory(configuration).getThriftClient(serverHost, serverPort);
	}

	public ThriftClient getClient() {
		return client;
	}

	public void setClient(ThriftClient client) {
		this.client = client;
	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString());
	}
}
