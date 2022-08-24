package com.guciowons.shoppingbasket.Basket;

import org.springframework.stereotype.Service;

@Service
public class BasketService {
    private final BasketSummarizer basketSummarizer;
    private final BasketDao basketDao;

    public BasketService(BasketSummarizer basketSummarizer, BasketDao basketDao) {
        this.basketSummarizer = basketSummarizer;
        this.basketDao = basketDao;
    }

    public Basket createBasket() {
        Basket newBasket = new Basket(basketDao.getAll().size());
        basketDao.save(newBasket);
        return newBasket;
    }

//    public BasketSummarized addProductToBasket(int basketId, int productId, int quantity) throws IllegalArgumentException{
//        return basketDao.findById(basketId)
//                .map(basket -> productDao.findById(productId)
//                        .map(product -> {
//                            basket.addProduct(productId, quantity);
//                            return basketSummarizer.summarizeBasket(basket.getContent(), productDao.getAll());
//                        })
//                        .orElseThrow(() -> {throw new IllegalArgumentException("No such basket");}))
//                .orElseThrow(() -> {throw new IllegalArgumentException("No such basket");});
//    }
//
//    public void removeProductFromBasket(int basketId, int productId, int quantity) throws IllegalArgumentException{
//        basketDao.findById(basketId).ifPresentOrElse(
//                basket -> productDao.findById(productId).ifPresentOrElse(
//                        product -> basket.removeProduct(productId, quantity),
//                        () -> {throw new IllegalArgumentException("No such product");}
//                ),
//                () -> {throw new IllegalArgumentException("No such basket");}
//        );
//    }
//
//    public BasketSummarized summarizeBasket(int basketId) {
//        return basketDao.findById(basketId).map(
//                basket -> basketSummarizer.summarizeBasket(basket.getContent(), productDao.getAll())
//                ).orElseThrow(() -> {throw new IllegalArgumentException("No such basket");});
//    }
}
