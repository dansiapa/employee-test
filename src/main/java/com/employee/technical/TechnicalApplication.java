package com.employee.technical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechnicalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalApplication.class, args);
		System.out.println("======================= Employee API is running =======================");
	}

}
