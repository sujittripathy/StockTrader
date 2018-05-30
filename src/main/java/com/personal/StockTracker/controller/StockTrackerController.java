package com.personal.StockTracker.controller;

import com.personal.StockTracker.configuration.StockTrackerProps;
import com.personal.StockTracker.model.StockPriceModel;
import com.personal.StockTracker.service.StockTradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/stock")
public class StockTrackerController {

	@Autowired
	private StockTrackerProps stockTrackerProps;

	@Autowired
	private StockTradingService stockTradingService;


//	public StockTrackerController(StockTradingService stockTradingService) {
//		this.stockTradingService = stockTradingService;
//	}

	@GetMapping("/latest-price")
	public String getLatestPrice(@RequestParam("sym") String sym){
		String endpoint = stockTrackerProps.getTradingEndpoint().concat(sym).concat("/book");
		Optional<StockPriceModel> stockPriceModel =
					Optional.ofNullable(stockTradingService.getRestTemplate().
									getForObject(endpoint,StockPriceModel.class));
		System.out.println(stockPriceModel);
		return stockPriceModel.get().getQuote().getLatestPrice();
	}
}
