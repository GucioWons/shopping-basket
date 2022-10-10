package com.guciowons.shoppingbasket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableTransactionManagement
public class ShoppingbasketApplication{
	public static void main(String[] args) {
		SpringApplication.run(ShoppingbasketApplication.class, args);
	}
}
