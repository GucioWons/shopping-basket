package com.guciowons.shoppingbasket.Product;

import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductProvider productProvider;
    private final ProductRepository productRepository;

    public ProductService(ProductProvider productProvider, ProductRepository productRepository) {
        this.productProvider = productProvider;
        this.productRepository = productRepository;
    }

    @Scheduled(fixedDelay = 3600000)
    public void refreshProductsInDatabase(){
        try {
            insertProducts();
        }catch (FeignException e){
            System.out.println("Error connecting to external api. Products will be downloaded in one hour");
        }
    }

    public void insertProducts(){
            productProvider.getProducts()
                    .forEach(externalProduct -> productRepository.findProductByExternalId(externalProduct.getExternalId()).ifPresentOrElse(
                            databaseProduct -> updateProducts(databaseProduct, externalProduct),
                            () -> productRepository.save(externalProduct)
                    ));
    }

    private void updateProducts(Product databaseProduct, Product externalProduct){
        externalProduct.setId(databaseProduct.getId());
        productRepository.save(externalProduct);
    }

    public List<Product> getProducts() {
       return productRepository.findAll();
    }

    public Optional<Product> getProductById(String productId){
        return productRepository.findById(productId);
    }
}
