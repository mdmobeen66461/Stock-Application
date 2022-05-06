package com.zensar.stockapplication.service;

import java.util.List;

import com.zensar.stockapplication.dtos.StockDto;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;

public interface StockService {
	
	List<StockDto> getAllStock(int pageNumber,int pageSize, String sortBy);
	StockDto getStock(long stockId)throws InvalidStockIdException;
	List<StockDto> getStockByItsName(String stockName);
	List<StockDto> getStockByItsNameAndPrice( String stockName,double price);
	StockDto createStock(StockDto stock, String token);
	String deleteStock(long id);
	StockDto updateStock( int stockId, StockDto stock)throws InvalidStockIdException;
	String deleteAllStocks();

}
