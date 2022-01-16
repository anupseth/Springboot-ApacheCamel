package com.microservices.camelmicroservicea.routes.b;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder{
	
	@Autowired
	private DeciderBean deciderBean;

	@Override
	public void configure() throws Exception {
		from("file:files/input")
		.routeId("Files-Input-route")
		.transform().body(String.class)
		.choice()
			.when(simple("${file:ext} ends with 'xml'"))
				.log("XML File")
			//.when(simple("${body} contains 'USD'"))
				.when(method(deciderBean))
				.log("Not a XML file but containes USD")
			.otherwise()
				.log("Not a XML file")
		.end()
		//.to("direct://log-file-values")
		.to("file:files/output");
		
		
		//creating a resuable route, so we can direct the control to this route from anywhere in the project
		from("direct:log-file-values")
		.log("-------------")
		.log("${messageHistory}")
		.log("-------------")
		.log("camelFileNameOnly = ${headers.camelFileNameOnly}")
		.log("Absolute path :  ${file:absolute.path}");
		
	}

}

@Component
class DeciderBean{
	
	Logger logger = LoggerFactory.getLogger(DeciderBean.class);
	
	public boolean isThisConditionMet(@Body String DeciderBean,
			@Headers Map<String,String> headers,
			@ExchangeProperties Map<String,String> exchangeProeprties) {
		
		logger.info(" Body : {}", DeciderBean);
		logger.info(" Headers : {}", headers);
		logger.info(" ExchangeProperties : {}", exchangeProeprties);
		return true;
	}
}
