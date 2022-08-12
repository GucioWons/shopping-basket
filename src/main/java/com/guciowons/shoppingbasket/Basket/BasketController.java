package com.guciowons.shoppingbasket.Basket;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @RequestMapping(value = "/{id}/{quantity}", method = RequestMethod.PUT)
    public ResponseEntity addProductToBasket(@PathVariable int id, @PathVariable int quantity){
        return basketService.addProductToBasket(id, quantity);
    }

    @RequestMapping(value = "/{id}/{quantity}", method = RequestMethod.DELETE)
    public ResponseEntity removeProductFromBasket(@PathVariable int id, @PathVariable int quantity){
        return basketService.removeProductFromBasket(id, quantity);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public BasketSummarized summarizeBasket(){
        return basketService.summarizeBasket();
    }
}
