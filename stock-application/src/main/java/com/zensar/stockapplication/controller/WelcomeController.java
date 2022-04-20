package com.zensar.stockapplication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	
	@RequestMapping(value="/test",method= {RequestMethod.GET,RequestMethod.POST})
	public void test() {
		System.out.println("Inside test method of Welcome Controller");
	}

}
