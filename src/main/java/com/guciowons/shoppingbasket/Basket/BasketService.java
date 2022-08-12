package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.ProductDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BasketService {
    private Basket basket = new Basket();

    private final ProductDao productDao;
    private final BasketSummarizer basketSummarizer;

    public BasketService(ProductDao productDao, BasketSummarizer basketSummarizer) {
        this.productDao = productDao;
        this.basketSummarizer = basketSummarizer;
    }

    public ResponseEntity<String> addProductToBasket(int id, int quantity) {
        return productDao.findById(id)
                .map(product -> {
                    basket.addProduct(id, quantity);
                    return new ResponseEntity<>("Done", HttpStatus.ACCEPTED);
                }).orElseGet(() -> new ResponseEntity<>("No such product", HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<String> removeProductFromBasket(int id, int quantity) {
        return productDao.findById(id)
                .map(product -> removeIfContains(id, quantity))
                .orElseGet(() -> new ResponseEntity<>("No such product", HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<String> removeIfContains(Integer productId, int quantity){
        if(basket.getContent().containsKey(productId)){
            basket.removeProduct(productId, quantity);
            return new ResponseEntity<>("Done", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("No such product in basket", HttpStatus.NOT_FOUND);
    }

    public BasketSummarized summarizeBasket() {
        return basketSummarizer.summarizeBasket(basket.getContent(), productDao);
    }
}
