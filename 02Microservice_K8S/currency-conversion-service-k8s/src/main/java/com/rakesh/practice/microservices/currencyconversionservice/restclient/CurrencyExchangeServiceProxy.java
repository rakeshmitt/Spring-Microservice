package com.rakesh.practice.microservices.currencyconversionservice.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rakesh.practice.microservices.currencyconversionservice.bean.CurrencyConversionBean;

/*
 * CURRENCY_EXCHANGE_SERVICE_HOST is the default parameter assigned by kubernetes
 * this would created problem if CURRENCY_EXCHANGE service is not up.
 * Hence we would be using a custom paramter to pass the specific value
 * for currency-exchange service
 */
//@FeignClient(name="currency-exchange", url="${CURRENCY_EXCHANGE_SERVICE_HOST: http://localhost}:8000")
@FeignClient(name="currency-exchange", url="${CURRENCY_EXCHANGE_URI: http://localhost}:8000")
public interface CurrencyExchangeServiceProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(
			@PathVariable("from") String from, 
			@PathVariable("to") String to);

}
