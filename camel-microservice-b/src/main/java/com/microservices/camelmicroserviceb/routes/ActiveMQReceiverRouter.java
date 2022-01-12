package com.microservices.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.microservices.camelmicroserviceb.pojo.CurrencyExchange;

@Component
public class ActiveMQReceiverRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		//timer
		//transform
		//queue
		
//		from("activemq:my-activemq-queue")
//		.log("received message from sender : ${body}");
		
		//JSON
		// CurrencyExchange
//		{
//			  "id": 1000,
//			  "from": "USD",
//			  "to": "INR",
//			  "conversionMultiple": 70
//			}
		from("activemq:my-activemq-queue")
		.unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
		.log("received message from sender : ${body}");
		
	}

}
