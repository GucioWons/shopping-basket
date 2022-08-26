package com.guciowons.shoppingbasket.Product;

import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductClient productClient;

    public ProductService(ProductClient productClient) {
        this.productClient = productClient;
    }

    public List<Product> getProducts() throws FeignException{
        return productClient.getProducts();
    }
}
