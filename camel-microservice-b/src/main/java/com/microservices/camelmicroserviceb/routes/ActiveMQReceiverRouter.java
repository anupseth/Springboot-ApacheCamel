package com.microservices.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQReceiverRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		//timer
		//transform
		//queue
		
		from("activemq:my-activemq-queue")
		.log("${body}");
	}

}
