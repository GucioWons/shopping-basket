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

    public String addProductToBasket(int id) {
        Product product = productDao.findById(id).orElse(null);
        if(product != null){
            basket.addProduct(product);
            return "Done";
        }
        return "No product";
    }

    public String removeProductFromBasket(int id) {
        Product product = productDao.findById(id).orElse(null);
        System.out.println(basket.getContent().toString());
        if(product != null){
            if(basket.getContent().contains(product)){
                basket.removeProduct(product);
                System.out.println(basket.getContent().toString());
                return "Done";
            }
            return "No product in basket";
        }
        return "No product";
    }

    public Basket summarizeBasket() {
        return basket;
    }
}
