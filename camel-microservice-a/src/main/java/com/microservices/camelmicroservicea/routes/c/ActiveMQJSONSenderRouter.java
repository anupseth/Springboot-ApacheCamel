package com.microservices.camelmicroservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMQJSONSenderRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		//timer
		//transform
		//queue
		
//		from("timer:active-mq-timer?period=10000")
//		.transform().constant("My Message for active mq")
//		.log("${body}")
//		.to("activemq:my-activemq-queue");
		
		
		from("file:files/json")
		.log("${body}")
		.to("activemq:my-activemq-queue");
	}

}
