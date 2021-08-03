package com.rakesh.practice.microservices.currencyexchangeservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakesh.practice.microservices.currencyexchangeservice.bean.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue,Long> {
	
	ExchangeValue findByFromIgnoreCaseAndToIgnoreCase(String from, String to);

}
