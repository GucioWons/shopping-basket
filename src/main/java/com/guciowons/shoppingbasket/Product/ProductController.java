package com.guciowons.shoppingbasket.Product;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity getProducts(){
        try {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(productService.getProducts());
        }catch(FeignException e){
            return ResponseEntity
                    .status(HttpStatus.GATEWAY_TIMEOUT)
                    .body("Unable to fetch products from external provider");
        }
    }
}
