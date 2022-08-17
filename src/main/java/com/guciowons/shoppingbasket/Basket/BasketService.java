package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.ProductDao;
import org.springframework.stereotype.Service;

@Service
public class BasketService {
    private final ProductDao productDao;
    private final BasketSummarizer basketSummarizer;
    private final BasketDao basketDao;

    public BasketService(ProductDao productDao, BasketSummarizer basketSummarizer, BasketDao basketDao) {
        this.productDao = productDao;
        this.basketSummarizer = basketSummarizer;
        this.basketDao = basketDao;
    }

    public void createBasket(Basket basket) {
        basketDao.save(basket);
    }

    public void addProductToBasket(int basketId, int productId, int quantity) throws IllegalArgumentException{
        basketDao.findById(basketId).ifPresentOrElse(
                basket -> productDao.findById(productId).ifPresentOrElse(
                        product -> basket.addProduct(productId, quantity),
                        () -> {throw new IllegalArgumentException("No such product");}
                ),
                () -> {throw new IllegalArgumentException("No such basket");}
        );
    }
//
//    public void removeProductFromBasket(int id, int quantity) throws IllegalArgumentException{
//        productDao.findById(id).ifPresentOrElse(
//                product -> basket.removeProduct(id, quantity),
//                () -> {throw new IllegalArgumentException("No such product");}
//        );
//    }
//
//    public BasketSummarized summarizeBasket() {
//        return basketSummarizer.summarizeBasket(basket.getContent(), productDao.getAll());
//    }
}
