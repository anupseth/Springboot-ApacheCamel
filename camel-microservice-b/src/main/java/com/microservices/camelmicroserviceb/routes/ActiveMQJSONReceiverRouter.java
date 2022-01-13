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
public class ActiveMQJSONReceiverRouter extends RouteBuilder{

	
	
	
	@Autowired
	private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;
	
	@Autowired
	private MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

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
		.bean(myCurrencyExchangeProcessor)
		.bean(myCurrencyExchangeTransformer)
		.log("received message from sender : ${body}");
		
	}

}

@Component
class MyCurrencyExchangeProcessor {
	
	Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);
	
	public void processMessage(CurrencyExchange currencyExchange) {
		
		logger.info("Conversion multiple : {}",currencyExchange.getConversionMultiple());
	}

}

@Component
class MyCurrencyExchangeTransformer {
	
	Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeTransformer.class);
	
	public CurrencyExchange transformMessage(CurrencyExchange currencyExchange) {
		
		currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));
		
		logger.info("Conversion multiple Transformed: {}",currencyExchange.getConversionMultiple());
		
		return currencyExchange;
	}

}
