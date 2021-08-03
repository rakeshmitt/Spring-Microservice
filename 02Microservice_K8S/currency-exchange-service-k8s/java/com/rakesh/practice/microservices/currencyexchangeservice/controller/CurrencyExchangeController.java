package com.rakesh.practice.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.practice.microservices.currencyexchangeservice.Repository.ExchangeValueRepository;
import com.rakesh.practice.microservices.currencyexchangeservice.bean.ExchangeValue;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository exchangeRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		logger.info("retrieve exchange value called with parameter with {} to {}", from, to);
		ExchangeValue exVal = exchangeRepository.findByFromIgnoreCaseAndToIgnoreCase(from, to);
		
		if(exVal == null) {
			throw new RuntimeException("Unable to find data for " + from + " to "+ to);
		}
		
		String port = environment.getProperty("local.server.port");
		
		String host = environment.getProperty("HOSTNAME");
		String version = "v11";
		
		exVal.setEnvironment(port + " " + version + " " + host);
		return exVal;
		
	}

}
