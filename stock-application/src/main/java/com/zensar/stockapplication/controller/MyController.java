package com.zensar.stockapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.stockapplication.dtos.StockDto;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;
import com.zensar.stockapplication.service.StockService;

@RestController
//@Controller if we use @Controller we have to add @ResponseBody annotation on every Method
@RequestMapping("/stocks")
//@RequestMapping(value="/stocks",produces = {MediaType.APPLICATION_XML_VALUE ,MediaType.APPLICATION_JSON_VALUE},consumes ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
//@CrossOrigin("*")
//@ApiIgnore to ignore Controller
public class MyController {
	
	@Autowired
	private StockService stockService;
	
	
	
	//Read all stock
	//http://localhost:5000/stocks
	//@GetMapping("/stocks")
	@RequestMapping( method=RequestMethod.GET,produces = {MediaType.APPLICATION_XML_VALUE ,MediaType.APPLICATION_JSON_VALUE})
	//@ResponseBody
	//http://localhost:5000/stocks?pageSize=10
	//@ApiOperation("Getting all Stocks")
	public List<StockDto> getAllStock(@RequestParam(value="pageNumber",defaultValue = "0",required = false)int pageNumber,@RequestParam(value="pageSize",defaultValue = "5",required = false)int pageSize,@RequestParam(value="sortBy",defaultValue = "stockId",required = false)String sortBy){
		return stockService.getAllStock(pageNumber,pageSize,sortBy);
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
	
	//@ApiOperation("Getting  Stock by using stock id")
	@RequestMapping(value="/{stockId}")
	//@ApiParam("stock id must b greater than 1")
	public StockDto getStock( @PathVariable long stockId) throws InvalidStockIdException {
		
		
		return stockService.getStock(stockId);
		
	} 
	
	@RequestMapping(value="/name/{stockName}",method=RequestMethod.GET)
	public List<StockDto> getStockByName(@PathVariable("stockName") String stockName){
		return stockService.getStockByItsName(stockName);
	}
	
	
	@RequestMapping(value="/name/{stockName}/price/{stockPrice}",method=RequestMethod.GET)
	public List<StockDto> getStockByName(@PathVariable("stockName") String stockName,@PathVariable("stockPrice") double stockPrice){
		return stockService.getStockByItsNameAndPrice(stockName,stockPrice);
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
	//@ApiOperation("Creating a stock by auth-token")
    public ResponseEntity<StockDto> createStock(@RequestBody StockDto stock,@RequestHeader("auth-token") String token) {
		
    	StockDto createStock =stockService.createStock(stock, token);

    	return new ResponseEntity<StockDto>(createStock,HttpStatus.CREATED);
		
	}
	//Delete specific stock
	//@DeleteMapping("/stocks/{stockId}")
   // @ApiOperation("Deleting stock using stock Id")
	@DeleteMapping("/{stockId}")
	//@ApiParam("Stock id must be greater than 1 to delete")
	public String deleteStock(@PathVariable("stockId") long id) {
		return stockService.deleteStock(id);
	}
	
	//@PutMapping("/stocks/{stockId}")
	@PutMapping(value ="/{stockId}",produces = {MediaType.APPLICATION_XML_VALUE ,MediaType.APPLICATION_JSON_VALUE},consumes ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	//@ApiOperation("Updating stock ")
	public StockDto updateStock(@PathVariable int stockId,@RequestBody StockDto stock) throws InvalidStockIdException {
				return stockService.updateStock(stockId, stock);
	}
	
//	@RequestMapping(value="/test",method= {RequestMethod.GET,RequestMethod.POST})
//	public void test() {
//		System.out.println("Inside test method");
//	}
//	
	//To Remove All stocks
	@DeleteMapping
	//@ApiOperation("Deleting all stocks ")
	public ResponseEntity<String> deleteAllStocks(){
		String returnResult=stockService.deleteAllStocks();
		
		return new ResponseEntity<String> (returnResult,HttpStatus.OK);
		
		//return stockService.deleteAllStocks()
		
	}
	
	
	//for Exceptions
	
	/*@ExceptionHandler(InvalidStockIdException.class)
	public String exceptionHandler() {
		return " Invalid Stock Id ";
	}*/
	
	

}
