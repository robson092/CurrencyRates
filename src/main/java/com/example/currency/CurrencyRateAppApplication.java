package com.example.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.currency.model", "com.example.currency.service", "com.example.currency.controller"})
public class CurrencyRateAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyRateAppApplication.class, args);
	}

}
