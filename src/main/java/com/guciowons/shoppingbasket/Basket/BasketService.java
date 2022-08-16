package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.ProductDao;
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

    public void addProductToBasket(int id, int quantity) throws IllegalArgumentException{
        productDao.findById(id).ifPresentOrElse(
                product -> basket.addProduct(id, quantity),
                () -> {throw new IllegalArgumentException("No such product");}
        );
    }

    public void removeProductFromBasket(int id, int quantity) throws IllegalArgumentException{
        productDao.findById(id).ifPresentOrElse(
                product -> removeIfContains(id, quantity),
                () -> {throw new IllegalArgumentException("No such product");}
        );
    }

    private void removeIfContains(Integer productId, int quantity) throws IllegalArgumentException{
        if(basket.getContent().containsKey(productId)){
            basket.removeProduct(productId, quantity);
        }else {
            throw new IllegalArgumentException("No such product in the basket");
        }
    }

    public BasketSummarized summarizeBasket() {
        return basketSummarizer.summarizeBasket(basket.getContent(), productDao);
    }
}
