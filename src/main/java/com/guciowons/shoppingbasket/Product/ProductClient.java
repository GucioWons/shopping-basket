package com.guciowons.shoppingbasket.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(url="${getstore.url}", name="PRODUCT-CLIENT")
public interface ProductClient {

    @GetMapping("/products")
    List<Product> getProducts();
    @GetMapping("/products/{productId}")
    Optional<Product> getProductById(@PathVariable int productId);
}
