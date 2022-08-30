package com.guciowons.shoppingbasket.Product;

import com.guciowons.shoppingbasket.Exception.NoExternalConnectionException;
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

    public List<Product> getProducts() throws NoExternalConnectionException {
        try {
            return productClient.getProducts();
        }catch(FeignException e){
            throw new NoExternalConnectionException("Cant connect with external api");
        }
    }

    public Optional<Product> getProductById(int productId){
        return productClient.getProductById(productId);
    }
}
