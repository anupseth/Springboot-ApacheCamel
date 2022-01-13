package com.microservices.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;

import com.microservices.camelmicroserviceb.pojo.CurrencyExchange;

//@Component
public class kafkaJsonReceiverRouter extends RouteBuilder{
	
	@Autowired
	private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;
	
	@Autowired
	private MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

	
	@Override
	public void configure() throws Exception {
	
		from("kafka:my-kafka-topic")
		.unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
		.bean(myCurrencyExchangeProcessor)
		.bean(myCurrencyExchangeTransformer)
		.log("received message from kafka : ${body}");
	}

}
