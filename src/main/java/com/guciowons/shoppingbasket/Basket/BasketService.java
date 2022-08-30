package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Exception.*;
import com.guciowons.shoppingbasket.Product.Product;
import com.guciowons.shoppingbasket.Product.ProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketService {
    private final BasketSummarizer basketSummarizer;
    private final BasketRepository basketRepository;
    private final ProductService productService;

    public BasketService(BasketSummarizer basketSummarizer, BasketRepository basketRepository, ProductService productService) {
        this.basketSummarizer = basketSummarizer;
        this.basketRepository = basketRepository;
        this.productService = productService;
    }

    public Basket createBasket() {
        Basket newBasket = new Basket(basketRepository.findAll().size());
        basketRepository.save(newBasket);
        return newBasket;
    }


    public BasketSummarized addProductToBasket(int basketId, int productId, int quantity) throws NoExternalConnectionException, NoProductException, NoBasketException{
        return basketRepository.findById(basketId)
                .map(basket -> addProductIfExists(productService.getProductById(productId), basket, quantity))
                .orElseThrow(() -> new NoBasketException("No such basket"));
    }

    private BasketSummarized addProductIfExists(Optional<Product> optionalProduct, Basket basket, int quantity){
        return optionalProduct
                .map(product -> {
                    basket.addProduct(product.getId(), quantity);
                    basketRepository.save(basket);
                    return basketSummarizer.summarizeBasket(basket.getContent(), productService.getProducts());
                })
                .orElseThrow(() -> new NoProductException("No such product!"));
    }


    public void removeProductFromBasket(int basketId, int productId, int quantity) throws NoExternalConnectionException, NoProductInBasketException, NoProductException, NoBasketException {
        basketRepository.findById(basketId).ifPresentOrElse(
                basket -> removeProductIfExists(productService.getProductById(productId), basket, quantity),
                    () -> {
                        throw new NoBasketException("No such basket!");
                    });
    }

    private void removeProductIfExists(Optional<Product> optionalProduct, Basket basket, int quantity){
        optionalProduct
                .ifPresentOrElse(product -> {
                        basket.removeProduct(product.getId(), quantity);
                        basketRepository.save(basket);
                    },
                    () -> {
                        throw new NoProductException("No such product!");
                });
    }


    public BasketSummarized summarizeBasket(int basketId) throws NoBasketException, NoExternalConnectionException{
        return basketRepository.findById(basketId).map(
                basket -> basketSummarizer.summarizeBasket(basket.getContent(), productService.getProducts())
        ).orElseThrow(() -> new NoBasketException("No such basket"));
    }
}
