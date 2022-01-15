package com.microservices.camelmicroserviceb.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.camelmicroserviceb.pojo.CurrencyExchange;

@RestController
public class CurrencyExchangeController {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange findConversionValue(@PathVariable String from, @PathVariable String to) {
		
		return new CurrencyExchange(1l,"USD","INR",BigDecimal.TEN);
	}
	
}
