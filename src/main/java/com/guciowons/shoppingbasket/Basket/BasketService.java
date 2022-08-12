package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.Product;
import com.guciowons.shoppingbasket.Product.ProductDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BasketService {
    private Basket basket = new Basket();

    private final ProductDao productDao;

    public BasketService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ResponseEntity addProductToBasket(int id, int quantity) {
        Product product = productDao.findById(id).orElse(null);
        if(product != null){
            basket.addProduct(product.getId(), quantity);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity removeProductFromBasket(int id, int quantity) {
        Product product = productDao.findById(id).orElse(null);
        if(product != null){
            return removeIfContains(product.getId(), quantity);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    private ResponseEntity removeIfContains(Integer productId, int quantity){
        if(basket.getContent().containsKey(productId)){
            basket.removeProduct(productId, quantity);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public Summary summarizeBasket() {
        return new Summary(productsMapToList(basket.getContent()));
    }

    private List<Summary.MultiProduct> productsMapToList(HashMap<Integer, Integer> basketProducts){
        List<Summary.MultiProduct> productsList = new ArrayList<>();
        basketProducts.forEach((key, value) -> {
            productDao.findById(key).ifPresent(
                    product -> productsList.add(new Summary.MultiProduct(product, value))
            );
        });
        return productsList;
    }
}
