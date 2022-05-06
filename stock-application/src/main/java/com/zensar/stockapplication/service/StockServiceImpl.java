package com.zensar.stockapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zensar.stockapplication.dtos.StockDto;
import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.exceptions.InvalidStockIdException;
import com.zensar.stockapplication.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private StockRepository stockRepository;
	
	//private ModelMapper modelMapper= new ModelMapper();
	
	@Autowired
	private ModelMapper modelMapper;

//	static List<Stock> stocks = new ArrayList<>();
//
//	static {
//		stocks.add(new Stock(1L, "stock1", "bse", 5000));
//		stocks.add(new Stock(2L, "stock2", "bse", 7000));
//	}

	
	//seraching by stockName method
	
	public List<StockDto> getStockByItsName(String stockName) {
		List<Stock> findStockByName = stockRepository.findName(stockName);
 
		List<StockDto> stocks=new ArrayList<StockDto>();
		
		for(Stock st:findStockByName) {
		  stocks.add(modelMapper.map(st, StockDto.class));
			
		}
		
		return stocks;
		
	}
	
	//seraching by stockName method
	
	
	public List<StockDto> getStockByItsNameAndPrice(String stockName,double price) {
		List<Stock> findStockByNameAndPrice = stockRepository.findNameAndPrice(stockName,price);
 
		List<StockDto> stocks=new ArrayList<StockDto>();
		
		for(Stock st:findStockByNameAndPrice) {
		  stocks.add(modelMapper.map(st, StockDto.class));
			
		}
		
		return stocks;
		
	}
	
	@Override
	public List<StockDto> getAllStock(int pageNumber,int pageSize,String sortBy) {
		
		Page<Stock> pageSizeStocks = stockRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(sortBy)));
		List<Stock> content = pageSizeStocks.getContent();
		List<StockDto> stockResponses=new ArrayList<StockDto>();
		for(Stock stock : content) {
			StockDto mapToResponse = modelMapper.map(stock,StockDto.class);
		stockResponses.add(mapToResponse);
		
		}
		return stockResponses;
	}
	@Override
	public StockDto getStock(long stockId) throws InvalidStockIdException{
		
		Optional<Stock> findByIdByOptional = stockRepository.findById(stockId);
		Stock stock=null;
		if(findByIdByOptional.isPresent()) {
			stock=findByIdByOptional.get();
			return modelMapper.map(stock, StockDto.class);
		}
		
		else {
			throw new InvalidStockIdException("Invalid stock id : "+stockId);
		}
		
	//	return stockRepository.findById(stockId).get();
		
		/*StockResponse stockResponse=new StockResponse();
		stockResponse.setStockId(stock.getStockId());
		stockResponse.setMarketName(stock.getMarketName());
		stockResponse.setName(stock.getName());
		stockResponse.setStockPrice(stock.getStockPrice());
		return stockResponse;*/
		
		
		
		
		
		//OR
	
//		Optional<Stock> optional=stockRepository.findById(stockId);
//		
//		if (optional.isPresent()) {
//			return optional.get();
//
//		} else {
//			return optional.orElseGet(() -> {
//				return new Stock();
//			});
//		}
//		
		
		/*Optional<Stock> stock1 = stocks.stream().filter(stock -> stock.getStockId() == stockId).findAny();

		if (stock1.isPresent()) {
			return stock1.get();

		} else {
			return stock1.orElseGet(() -> {
				return new Stock();
			});
		}*/

	}

	@Override
	public StockDto createStock(StockDto stock, String token) {
		/*if(token.equals("mm66461")) {
			stocks.add(stock);
			return stock;
			
			}else {
				//return new ResponseEntity<Stock>(HttpStatus.BAD_REQUEST);
				return null;
			}*/
		
		//	return new ResponseEntity<Stock>(stock,HttpStatus.CREATED);
		
		Stock newStock= modelMapper.map(stock, Stock.class);
		if(token.equals("mm66461")) {
			Stock save= stockRepository.save(newStock);
			return modelMapper.map(save,StockDto.class);
			
			}else {
				//return new ResponseEntity<Stock>(HttpStatus.BAD_REQUEST);
				return null;
			}
	
	}

	@Override
	public String deleteStock(long id) {
	/*	for(Stock stock:stocks) {
			if(stock.getStockId()==id) {
				stocks.remove(stock);
				
				return "Deleted  "+id;
			}
			
		}
		return "Sorry id Is not there";
		*/
		
		stockRepository.deleteById(id);
		return "Deleted  "+id;
		
	}

	@Override
	public StockDto updateStock(int stockId, StockDto stock) throws InvalidStockIdException {
		/*Stock avaibleStock=getStock(stockId);
		avaibleStock.setMarketName(stock.getMarketName());
		avaibleStock.setName(stock.getName());
		avaibleStock.setStockPrice(stock.getStockPrice());
		
		return avaibleStock;*/
		
		//Stock avaibleStock=getStock(stockId);
		
		StockDto stockResponse =getStock(stockId);
		Stock stock2=modelMapper.map(stockResponse ,Stock.class);
		if(stock2!=null) {
		stock2.setStockPrice(stock.getStockPrice());
		stock2.setMarketName(stock.getMarketName());
		stock2.setName(stock.getName());
		stock2.setStockId(stockId);
		Stock stock3= stockRepository.save(stock2);
		//return mapToResponse(stock3);
		
		return modelMapper.map(stock3,StockDto.class);
		}

		return null;
		}

	@Override
	public String deleteAllStocks() {
		stockRepository.deleteAll();
		return "All Stocks Deleted";
	}
	
	
	/*public Stock mapToStock(StockDto stockDto) {
		Stock newStock=new Stock();
		newStock.setMarketName(stockDto.getMarketName());
		newStock.setName(stockDto.getName());
		newStock.setStockPrice(stockDto.getStockPrice());
		
		return newStock;
	}*/
	
	
	
	
	/*private StockDto mapToDto(Stock stock) {

		StockDto stockResponse=new StockDto();
		stockResponse.setStockId(stock.getStockId());
		stockResponse.setStockPrice(stock.getStockPrice());
		stockResponse.setMarketName(stock.getMarketName());
		stockResponse.setName(stock.getName());

		return stockResponse;
		}

	*/
	

}
