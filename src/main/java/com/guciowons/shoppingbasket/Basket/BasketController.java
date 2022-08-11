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

    @GetMapping("/add/{id}/{quantity}")
    public String addProductToBasket(@PathVariable int id, @PathVariable int quantity){
        return basketService.addProductToBasket(id, quantity);
    }

    @GetMapping("/remove/{id}/{quantity}")
    public String removeProductFromBasket(@PathVariable int id, @PathVariable int quantity){
        return basketService.removeProductFromBasket(id, quantity);
    }

    @GetMapping("/summarize")
    public Summary summarizeBasket(){
        return basketService.summarizeBasket();
    }
}
