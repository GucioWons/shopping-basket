package com.guciowons.shoppingbasket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShoppingbasketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingbasketApplication.class, args);
	}

}
