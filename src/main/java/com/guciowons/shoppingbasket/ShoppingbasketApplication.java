package com.guciowons.shoppingbasket;

import com.guciowons.shoppingbasket.Product.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShoppingbasketApplication implements CommandLineRunner{
	private final ProductService productService;

	public ShoppingbasketApplication(ProductService productService) {
		this.productService = productService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingbasketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		productService.insertProducts();
	}
}
