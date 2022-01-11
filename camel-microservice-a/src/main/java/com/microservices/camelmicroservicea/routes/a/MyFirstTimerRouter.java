package com.microservices.camelmicroservicea.routes.a;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFirstTimerRouter extends RouteBuilder{

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
		//.transform().constant("My Constant message")
		.transform().constant("Local time : "+ LocalDateTime.now())
		.to("log:first-timer");
		
		
	}

}
