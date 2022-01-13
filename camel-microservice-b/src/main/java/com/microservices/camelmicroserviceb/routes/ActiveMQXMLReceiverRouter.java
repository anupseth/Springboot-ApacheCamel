package com.microservices.camelmicroserviceb.routes;

import java.math.BigDecimal;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservices.camelmicroserviceb.pojo.CurrencyExchange;

@Component
public class ActiveMQXMLReceiverRouter extends RouteBuilder{

	
	
	
	@Autowired
	private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;
	
	@Autowired
	private MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

	@Override
	public void configure() throws Exception {
		
		from("activemq:my-activemq-xml-queue")
		.unmarshal()
		.jacksonxml(CurrencyExchange.class)
		.bean(myCurrencyExchangeProcessor)
		.bean(myCurrencyExchangeTransformer)
		.log("received message from XML sender : ${body}");
		
	}

}

