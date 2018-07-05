package com.personal.StockTracker.service;

import com.personal.StockTracker.exception.WatchListException;
import com.personal.StockTracker.persistence.WatchList;
import com.personal.StockTracker.repository.WatchListRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Getter
public class StockTradingService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WatchListRepository watchListRepository;

//	@Autowired
//	public StockTradingService(RestTemplate restTemplate) {
//		this.restTemplate = restTemplate;
//	}

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
