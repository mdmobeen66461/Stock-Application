package com.zensar.stockapplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties.ConfigureAction;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource("beans.xml") for xml configuration
public class StockApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
//		Stock s=new Stock();
//		
//		s.getName();
//		s.setMarketName("nft");
//		System.out.println(s.getMarketName());
		
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return super.configure(builder);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}


