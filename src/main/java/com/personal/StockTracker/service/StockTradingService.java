package com.personal.StockTracker.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Getter
public class StockTradingService {

	@Autowired
	private RestTemplate restTemplate;

//	@Autowired
//	public StockTradingService(RestTemplate restTemplate) {
//		this.restTemplate = restTemplate;
//	}
}
