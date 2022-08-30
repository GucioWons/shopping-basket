package com.guciowons.shoppingbasket.Product;

import com.guciowons.shoppingbasket.Exception.NoExternalConnectionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductClient productClient;
    private final ProductRepository productRepository;

    public ProductService(ProductClient productClient, ProductRepository productRepository) {
        this.productClient = productClient;
        this.productRepository = productRepository;
    }

    public void insertProducts(){
        productRepository.insert(productClient.getProducts());
    }

    public List<Product> getProducts() throws NoExternalConnectionException {
       return productRepository.findAll();
    }

    public Optional<Product> getProductById(int productId){
        return productClient.getProductById(productId);
    }
}
