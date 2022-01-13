package com.microservices.camelmicroservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class KafkaJsonSenderRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		from("file:files/json")
		.log("${body}")
		.to("kafka:my-kafka-topic");
	}

}
