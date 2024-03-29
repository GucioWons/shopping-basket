package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Exception.*;
import com.guciowons.shoppingbasket.Product.ProductService;
import org.springframework.stereotype.Service;


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
        return basketRepository.save(new Basket());
    }


    public Basket addProductToBasket(String basketId, String productId, int quantity) throws NoProductException, NoBasketException{
        return basketRepository.findById(basketId)
                .map(basket -> addProductIfExists(basket, productId, quantity))
                .orElseThrow(() -> new NoBasketException("No such basket"));
    }

    private Basket addProductIfExists(Basket basket, String productId, int quantity){
        return productService.getProductById(productId)
                .map(product -> {
                    basket.addProduct(product.getId(), quantity);
                    return basketRepository.save(basket);
                })
                .orElseThrow(() -> new NoProductException("No such product!"));
    }


    public Basket removeProductFromBasket(String basketId, String productId, int quantity) throws NoProductInBasketException, NoProductException, NoBasketException {
        return basketRepository.findById(basketId)
                .map(basket -> removeProductIfExists(basket, productId, quantity))
                .orElseThrow(() -> new NoBasketException("No such basket"));
    }

    private Basket removeProductIfExists(Basket basket, String productId, int quantity){
        return productService.getProductById(productId)
                .map(product -> {
                    basket.removeProduct(product.getId(), quantity);
                    return basketRepository.save(basket);
                })
                .orElseThrow(() -> new NoProductException("No such product!"));
    }


    public BasketSummarized summarizeBasket(String basketId) throws NoBasketException{
        return basketRepository.findById(basketId).map(
                basket -> basketSummarizer.summarizeBasket(basket.getContent(), productService.getProducts())
        ).orElseThrow(() -> new NoBasketException("No such basket"));
    }
}
