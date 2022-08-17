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

//    public void addProductToBasket(int id, int quantity) throws IllegalArgumentException{
//        productDao.findById(id).ifPresentOrElse(
//                product -> basket.addProduct(id, quantity),
//                () -> {throw new IllegalArgumentException("No such product");}
//        );
//    }
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
