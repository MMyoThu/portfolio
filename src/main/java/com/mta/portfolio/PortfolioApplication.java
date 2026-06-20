package com.mta.portfolio;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortfolioApplication {

	static {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Yangon"));
	}

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}

}
