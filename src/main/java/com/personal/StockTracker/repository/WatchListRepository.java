package com.personal.StockTracker.repository;

import com.personal.StockTracker.persistence.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepository extends JpaRepository<WatchList,Integer> {
}
