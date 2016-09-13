package com.ikamobile.pa.common.jms;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

public abstract class AbstractProducer<T> implements Producer<T>{

	protected String destinationName;
	protected Destination destination;
	protected JmsTemplate jmsTemplate;
	
	
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Destination getDestion() {
		return this.destination;
	}

	@Override
	public void send(T message) {

	}

	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	
}
