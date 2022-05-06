package com.zensar.stockapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zensar.stockapplication.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{
	//seraching by stockName
	//List<Stock> findByName(String name);
	//List<Stock> findByNameAndStockPrice(String name,double price);
	
	//@Query("From MyStock s where s.name=:stockName")
	List<Stock> findName(@Param("stockName")String name);
	//@Query(value="From MyStock s where s.name=:stockName And s.stockPrice=:stockPrice",nativeQuery = false)
	List<Stock> findNameAndPrice(@Param("stockName") String name,@Param("stockPrice")double price);
	
	

}
