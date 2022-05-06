package com.zensar.stockapplication.exceptions;

import java.time.LocalTime;

import lombok.Data;

@Data
public class CustomErrorResponse {
	
	private String error;
	private LocalTime localTime;
}
