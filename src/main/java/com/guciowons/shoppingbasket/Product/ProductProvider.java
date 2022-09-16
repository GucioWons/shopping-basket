package com.guciowons.shoppingbasket.Product;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductProvider {
    private final ExternalProductClient externalProductClient;

    public ProductProvider(ExternalProductClient externalProductClient) {
        this.externalProductClient = externalProductClient;
    }

    public List<Product> getProducts(){
        return externalProductClient.getExternalProducts().stream()
                .map(externalProduct -> new Product(externalProduct.getId(), externalProduct.getTitle(), externalProduct.getPrice(), externalProduct.getDescription()))
                .toList();
    }
}
