package com.microservices.camelmicroservicea.routes.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:files/input")
		.routeId("Files-Input-route")
		.transform().body(String.class)
		.choice()
			.when(simple("${file:ext} ends with 'xml'"))
				.log("XML File")
			.when(simple("${body} contains 'USD'"))
				.log("Not a XML file but containes USD")
			.otherwise()
				.log("Not a XML file")
		.end()
		.log("-------------")
		.log("${messageHistory}")
		.log("-------------")
		.log("camelFileNameOnly = ${headers.camelFileNameOnly}")
		.log("Absolute path :  ${file:absolute.path}")
		.to("file:files/output");
		
	}

}
