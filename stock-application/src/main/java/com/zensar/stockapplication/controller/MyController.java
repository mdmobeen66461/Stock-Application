package com.zensar.stockapplication.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.stockapplication.entity.Stock;

@RestController
//@Controller if we use @Controller we have to add @ResponseBody annotation on every Method
@RequestMapping("/stocks")
//@RequestMapping(value="/stocks",produces = {MediaType.APPLICATION_XML_VALUE ,MediaType.APPLICATION_JSON_VALUE},consumes ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
//@CrossOrigin("*")
public class MyController {
	
	List<Stock> stocks=new ArrayList<>();
	
	
	public  MyController() {
		stocks.add(new Stock(1L, "stock1", "bse", 5000));
		stocks.add(new Stock(2L, "stock2", "bse", 7000));
	}
	
	//Read all stock
	//http://localhost:5000/stocks
	//@GetMapping("/stocks")
	@RequestMapping( method=RequestMethod.GET,produces = {MediaType.APPLICATION_XML_VALUE ,MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	public List<Stock> getAllStock(){
		return stocks;
	}
	
	//Read specific stock
	//http:localhost:5000/stocks/{stockId}
	//@GetMapping("/stocks/{stockId}")
	//@RequestMapping(value="/stocks/{stockId}", method=RequestMethod.GET)
	/*@RequestMapping(value="/{stockId}", method=RequestMethod.GET)
	public Stock getStock(@PathVariable long stockId) {
		
		for(Stock stock:stocks) {
			if(stock.getStockId()==stockId) {
				return stock;
			}
			
		}
		return null;
		
	}*/
	
	
	@RequestMapping(value="/{stockId}", method=RequestMethod.GET,produces = {MediaType.APPLICATION_XML_VALUE ,MediaType.APPLICATION_JSON_VALUE},consumes ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Stock getStock(@PathVariable long stockId) {
		
		
		Optional<Stock> stock1=stocks.stream().filter(stock->stock.getStockId()==stockId).findAny();
		
		if(stock1.isPresent()) {
			return stock1.get();
			
		}else {
			return stock1.orElseGet(()->{return new Stock();});
		}
		
		
		
	}
	
	
	
	/*@GetMapping("/stocks")
	public Stock getStockByRequestParam(@RequestParam(required = false,value="id",defaultValue = "1") long id) {
		
		for(Stock stock:stocks) {
			if(stock.getStockId()==id) {
				return stock;
			}
			
		}
		return null;
		
	}*/
	
	//@PostMapping("/stocks")
	//Create a new stock
	//@RequestMapping(value="/stocks",method = RequestMethod.POST)
	//@RequestMapping(method = RequestMethod.POST)
    @PostMapping(produces = {MediaType.APPLICATION_XML_VALUE ,MediaType.APPLICATION_JSON_VALUE},consumes ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<Stock> createStock(@RequestBody Stock stock,@RequestHeader("auth-token") String token) {
		
		if(token.equals("mm66461")) {
		stocks.add(stock);
		
		}else {
			return new ResponseEntity<Stock>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Stock>(stock,HttpStatus.CREATED);
	}
	//Delete specific stock
	//@DeleteMapping("/stocks/{stockId}")
	@DeleteMapping("/{stockId}")
	public String deleteStock(@PathVariable("stockId") long id) {
		for(Stock stock:stocks) {
			if(stock.getStockId()==id) {
				stocks.remove(stock);
				
				return "Deleted  "+id;
			}
			
		}
		return "Sorry id Is not there";
	}
	
	//@PutMapping("/stocks/{stockId}")
	@PutMapping(value ="/{stockId}",produces = {MediaType.APPLICATION_XML_VALUE ,MediaType.APPLICATION_JSON_VALUE},consumes ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Stock updateStock(@PathVariable int stockId,@RequestBody Stock stock) {
		Stock avaibleStock=getStock(stockId);
		avaibleStock.setMarketName(stock.getMarketName());
		avaibleStock.setName(stock.getName());
		avaibleStock.setStockPrice(stock.getStockPrice());
		
		return avaibleStock;
		
	}
	
//	@RequestMapping(value="/test",method= {RequestMethod.GET,RequestMethod.POST})
//	public void test() {
//		System.out.println("Inside test method");
//	}
//	
	//To Remove All stocks
	@DeleteMapping
	public ResponseEntity<String> deleteAllStocks(){
		stocks.removeAll(stocks);
		return new ResponseEntity<String>("All Stocks Deleted",HttpStatus.OK);
	}
	
	

}
