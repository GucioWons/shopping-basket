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

    public String addProductToBasket(int id, int quantity) {
        Product product = productDao.findById(id).orElse(null);
        if(product != null){
            basket.addProduct(product, quantity);
            return "Done";
        }
        return "No product";
    }

    public String removeProductFromBasket(int id, int quantity) {
        Product product = productDao.findById(id).orElse(null);
        if(product != null){
            return removeIfContains(product, quantity);
        }
        return "No product";
    }

    private String removeIfContains(Product product, int quantity){
        if(basket.getContent().containsKey(product)){
            basket.removeProduct(product, quantity);
            return "Done";
        }
        return "No product in basket";
    }

    public Basket summarizeBasket() {
        return basket;
    }
}
