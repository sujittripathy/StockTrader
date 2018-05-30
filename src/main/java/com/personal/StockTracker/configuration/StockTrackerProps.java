package com.personal.StockTracker.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class StockTrackerProps {

	@Value("${trading.endpoint}")
	private String tradingEndpoint;
}
