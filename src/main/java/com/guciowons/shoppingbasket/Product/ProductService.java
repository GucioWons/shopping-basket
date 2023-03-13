package com.guciowons.shoppingbasket.Product;

import com.guciowons.shoppingbasket.Exception.NoExternalConnectionException;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductProvider productProvider;
    private final ProductRepository productRepository;
    private final TransProductService transProductService;

    public ProductService(ProductProvider productProvider, ProductRepository productRepository, TransProductService transProductService) {
        this.productProvider = productProvider;
        this.productRepository = productRepository;
        this.transProductService = transProductService;
    }

    @Scheduled(fixedDelay = 3600000)
    public void refreshProductsInDatabase(){
        try {
            insertProducts();
        }catch (FeignException e){
            throw new NoExternalConnectionException("Error connecting to external api. Products will be downloaded in one hour");
        }
    }

    private void insertProducts(){
            productProvider.getProducts()
                    .forEach(externalProduct -> productRepository.findProductByExternalId(externalProduct.getExternalId()).ifPresentOrElse(
                            databaseProduct -> transProductService.updateProduct(databaseProduct, externalProduct),
                            () -> transProductService.insertProduct(externalProduct)
                    ));
    }

    public List<Product> getProducts() {
       return productRepository.findAll();
    }

    public Optional<Product> getProductById(String productId){
        return productRepository.findById(productId);
    }
}
