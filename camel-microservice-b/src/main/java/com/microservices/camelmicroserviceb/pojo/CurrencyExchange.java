package com.microservices.camelmicroserviceb.pojo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CurrencyExchange {
	
	private long id;
	private String from;
	private String to;
	private BigDecimal conversionMultiple;

}
