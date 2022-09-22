package com.guciowons.shoppingbasket.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url="${services.service}", name="PRODUCT-CLIENT", decode404 = true)
public interface ExternalProductClient {

    @GetMapping("/products")
    List<ExternalProduct> getExternalProducts();
}
