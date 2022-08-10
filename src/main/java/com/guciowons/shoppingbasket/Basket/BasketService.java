package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.Product;
import com.guciowons.shoppingbasket.Product.ProductDao;
import org.springframework.stereotype.Service;

@Service
public class BasketService {
    private Basket basket = new Basket();

    private final ProductDao productDao;

    public BasketService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public String addProduct(int id) {
        Product product = productDao.findById(id).orElse(null);
        if(product != null){
            basket.addProduct(product);
            return "Done";
        }
        return "No product";
    }
}
