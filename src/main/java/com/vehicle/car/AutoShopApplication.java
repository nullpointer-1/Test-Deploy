package com.vehicle.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutoShopApplication {

	public static void main(String[] args) {
		System.out.println("DB_URL: " + System.getenv("DB_URL"));
System.out.println("DB_USERNAME: " + System.getenv("DB_USERNAME"));
System.out.println("DB_PASSWORD: " + System.getenv("DB_PASSWORD"));

		SpringApplication.run(AutoShopApplication.class, args);
	}

}
