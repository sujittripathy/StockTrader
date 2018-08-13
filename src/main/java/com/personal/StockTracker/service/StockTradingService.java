package com.personal.StockTracker.service;

import com.personal.StockTracker.configuration.StockTrackerProps;
import com.personal.StockTracker.exception.WatchListException;
import com.personal.StockTracker.model.StockPriceModel;
import com.personal.StockTracker.persistence.WatchList;
import com.personal.StockTracker.repository.WatchListRepository;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Getter
public class StockTradingService {

	private final static Logger LOGGING =
			LoggerFactory.getLogger(StockTradingService.class);
	@Autowired
	private StockTrackerProps stockTrackerProps;

	private RestTemplate restTemplate;

	@Autowired
	private WatchListRepository watchListRepository;

	public StockTradingService() {
		this.restTemplate = new RestTemplateBuilder().build();
	}

	public String getLatestStockPrice(String tick_symbol){
		String endpoint = stockTrackerProps.getTradingEndpoint().concat(tick_symbol).concat("/book");
		LOGGING.info("endpoint > " + endpoint);
		Optional<StockPriceModel> stockPriceModel =
				Optional.ofNullable(restTemplate.
						getForObject(endpoint,StockPriceModel.class));
		LOGGING.info("stockPriceModel : " + stockPriceModel);
		return stockPriceModel.get().getQuote().getLatestPrice();
	}

	public WatchList addToWatchList(String tickerSymbol) throws WatchListException{
		try {
			return watchListRepository.save(createWatchList(tickerSymbol));
		} catch (Exception e) {
			throw new WatchListException("! Exception while adding ticker !" + e.getMessage()) ;
		}
	}

	private WatchList createWatchList(String ts){
		return WatchList.builder()
								.tickerSymbol(ts)
								.build();
	}
}
