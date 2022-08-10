package com.guciowons.shoppingbasket.Basket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/add/{id}")
    public String addProductToBasket(@PathVariable int id){
        return basketService.addProductToBasket(id);
    }

    @GetMapping("/remove/{id}")
    public String removeProductFromBasket(@PathVariable int id){
        return basketService.removeProductFromBasket(id);
    }

    @GetMapping("/summarize")
    public Basket summarizeBasket(){
        return basketService.summarizeBasket();
    }
}
