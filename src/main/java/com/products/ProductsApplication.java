package com.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = {"com.products"})
@EnableR2dbcRepositories
public class ProductsApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProductsApplication.class, args);
		System.out.println("DB_HOST: " + System.getenv("DB_HOST"));
		System.out.println("DB_PORT: " + System.getenv("DB_PORT"));
		System.out.println("DB_NAME: " + System.getenv("DB_NAME"));
	}

}
