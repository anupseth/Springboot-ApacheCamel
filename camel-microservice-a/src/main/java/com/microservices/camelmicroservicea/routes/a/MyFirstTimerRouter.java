package com.microservices.camelmicroservicea.routes.a;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyFirstTimerRouter extends RouteBuilder{
	
	@Autowired
	private GetCurrentTimeBean getCurrentTimeBean;

	
	@Autowired
	private SimpleLoggingProcesssingComponent simpleLoggingProcesssingComponent;

	
	// in configure method we create all the routes.
	@Override
	public void configure() throws Exception {
		// Normal scenario we do this
		// read from route
		// transformation
		// write to database
		
		// but for now lets try this scenario
		// timer
		// transform
		// log
		
		// this is timer endpoint and log end point
		from("timer:first-timer")
		.log("${body}")
		.transform().constant("My Constant message")
		.log("${body}")
		//.transform().constant("Local time : "+ LocalDateTime.now())
		.bean(getCurrentTimeBean)
		.log("${body}")
		.bean(simpleLoggingProcesssingComponent)
		.log("${body}")
		.to("log:first-timer");
		
		
	}

}

@Component
class GetCurrentTimeBean{
	
	public String getCurrentTime() {
		return "Time now is " + LocalDateTime.now();
	}
}


@Component
class SimpleLoggingProcesssingComponent{
	
	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcesssingComponent.class);
	
	public void process(String message) {
		logger.info("SimpleLoggingProcesssingComponent - {}",message);
	}
}
