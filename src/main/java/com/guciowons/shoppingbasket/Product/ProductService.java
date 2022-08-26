package com.guciowons.shoppingbasket.Product;

import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductClient productClient;

    public ProductService(ProductClient productClient) {
        this.productClient = productClient;
    }

    public List<Product> getProducts() throws FeignException{
        return productClient.getProducts();
    }

    public Optional<Product> getProductById(int productId){
        return productClient.getProductById(productId);
    }
}
