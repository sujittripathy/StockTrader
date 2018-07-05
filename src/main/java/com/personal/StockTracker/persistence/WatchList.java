package com.personal.StockTracker.persistence;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "WATCHLIST")
@Getter
@Setter
@Builder
public class WatchList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "TICKER_SYMBOL")
	private String tickerSymbol;

	@Column(name = "DATE_ADDED")
	private LocalDateTime dateAdded;
}
