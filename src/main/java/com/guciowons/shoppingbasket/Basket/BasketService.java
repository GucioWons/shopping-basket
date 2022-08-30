package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Exception.*;
import com.guciowons.shoppingbasket.Product.ProductService;
import org.springframework.stereotype.Service;

@Service
public class BasketService {
    private final BasketSummarizer basketSummarizer;
    private final BasketDao basketDao;
    private final ProductService productService;

    public BasketService(BasketSummarizer basketSummarizer, BasketDao basketDao, ProductService productService) {
        this.basketSummarizer = basketSummarizer;
        this.basketDao = basketDao;
        this.productService = productService;
    }

    public Basket createBasket() {
        Basket newBasket = new Basket(basketDao.getAll().size());
        basketDao.save(newBasket);
        return newBasket;
    }

    public BasketSummarized addProductToBasket(int basketId, int productId, int quantity) throws NoExternalConnectionException, NoProductException, NoBasketException{
        return basketDao.findById(basketId)
                .map(basket -> productService.getProductById(productId)
                        .map(product -> {
                            basket.addProduct(productId, quantity);
                            System.out.println(product);
                            return basketSummarizer.summarizeBasket(basket.getContent(), productService.getProducts());
                        })
                        .orElseThrow(() -> new NoProductException("No such product!")))
                .orElseThrow(() -> new NoBasketException("No such basket"));
    }

    public void removeProductFromBasket(int basketId, int productId, int quantity) throws NoExternalConnectionException, NoProductInBasketException, NoProductException, NoBasketException {
        basketDao.findById(basketId).ifPresentOrElse(
                basket -> productService.getProductById(productId).ifPresentOrElse(
                        product -> basket.removeProduct(productId, quantity),
                        () -> {
                            throw new NoProductException("No such product!");
                        }),
                    () -> {
                        throw new NoBasketException("No such basket!");
                    });
    }

    public BasketSummarized summarizeBasket(int basketId) throws NoBasketException, NoExternalConnectionException{
        return basketDao.findById(basketId).map(
                basket -> basketSummarizer.summarizeBasket(basket.getContent(), productService.getProducts())
        ).orElseThrow(() -> new NoBasketException("No such basket"));
    }
}
