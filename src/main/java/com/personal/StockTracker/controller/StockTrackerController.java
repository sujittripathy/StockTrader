package com.personal.StockTracker.controller;

import com.personal.StockTracker.configuration.StockTrackerProps;
import com.personal.StockTracker.model.StockPriceModel;
import com.personal.StockTracker.persistence.WatchList;
import com.personal.StockTracker.service.StockTradingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/stock")
public class StockTrackerController {

	private final static Logger LOGGING =
				LoggerFactory.getLogger(StockTrackerController.class);

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
		LOGGING.info("stockPriceModel : " + stockPriceModel);
		return stockPriceModel.get().getQuote().getLatestPrice();
	}

	@PostMapping("/watchlist/add/{ticker}")
	public void addToWatchList(@PathVariable String ticker){
		WatchList w = stockTradingService.addToWatchList(ticker);
		LOGGING.info(" Ticker added to watchlist : " + w.getId());
	}
}
