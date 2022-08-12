package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.Product;
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

    public BasketSummarized summarizeBasket() {
        return new BasketSummarized(
                basketSummarizer.productsMapToList(basket.getContent(), productDao),
                basketSummarizer.countPrices(basket.getContent(), productDao));
    }
}
