package com.zensar.stockapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource("beans.xml") for xml configuration
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
//		Stock s=new Stock();
//		
//		s.getName();
//		s.setMarketName("nft");
//		System.out.println(s.getMarketName());
//		
		
		
		
	}

}
