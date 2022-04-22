package com.zensar.stockapplication.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.zensar.stockapplication.entity.Stock;

public interface StockService {
	
	List<Stock> getAllStock();
	Stock getStock(long stockId);
	Stock createStock(Stock stock, String token);
	Stock updateStock( int stockId, Stock stock);
	String deleteAllStocks();

}
