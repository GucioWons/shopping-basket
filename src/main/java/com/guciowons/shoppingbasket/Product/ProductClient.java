package com.guciowons.shoppingbasket.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url="https://fakestoreapi.com", name="PRODUCT-CLIENT")
public interface ProductClient {

    @GetMapping("/products")
    List<Product> getProducts();
}
