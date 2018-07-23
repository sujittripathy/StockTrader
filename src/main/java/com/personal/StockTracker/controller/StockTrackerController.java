package com.personal.StockTracker.controller;

import com.personal.StockTracker.persistence.WatchList;
import com.personal.StockTracker.service.StockTradingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@RequestMapping("/stock")
@Api("StockTrackerController")
public class StockTrackerController {

	private final static Logger LOGGING =
				LoggerFactory.getLogger(StockTrackerController.class);


	private StockTradingService stockTradingService;

	@Autowired
	private DataSource dataSource;


	public StockTrackerController(StockTradingService stockTradingService) {
		this.stockTradingService = stockTradingService;
	}

	@GetMapping("/latest-price")
	public String getLatestPrice(@ApiParam @RequestParam("sym") String sym){
		return stockTradingService.getLatestStockPrice(sym);
	}

	@PostMapping("/watchlist/add/{ticker}")
	public void addToWatchList(@PathVariable String ticker){
		WatchList w = stockTradingService.addToWatchList(ticker);
		LOGGING.info(" Ticker added to watchlist : " + w.getId());
	}
}
