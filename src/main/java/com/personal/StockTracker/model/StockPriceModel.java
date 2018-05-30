package com.personal.StockTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class StockPriceModel {

	private Quote quote;

	@Getter
	@Setter
	public static class Quote {
		private String symbol;
		private String latestPrice;

		@Override
		public String toString() {
			return "Quote{" +
					"symbol='" + symbol + '\'' +
					", latestPrice='" + latestPrice + '\'' +
					'}';
		}
	}

	@Override
	public String toString() {
		return "StockPriceModel{" +
				"quote=" + quote +
				'}';
	}
}
