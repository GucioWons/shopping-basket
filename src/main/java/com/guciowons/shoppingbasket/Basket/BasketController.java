package com.guciowons.shoppingbasket.Basket;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baskets")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    public ResponseEntity<Basket> createBasket(){
        return new ResponseEntity<>(basketService.createBasket(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{basketId}/{productId}/{quantity}")
    public ResponseEntity<Basket> addProductToBasket(@PathVariable String basketId, @PathVariable String productId, @PathVariable int quantity){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(basketService.addProductToBasket(basketId, productId, quantity));
    }

    @DeleteMapping(value = "/{basketId}/{productId}/{quantity}")
    public ResponseEntity<Basket> removeProductFromBasket(@PathVariable String basketId, @PathVariable String productId, @PathVariable int quantity){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(basketService.removeProductFromBasket(basketId, productId, quantity));
    }

    @GetMapping(value="/{basketId}")
    public ResponseEntity<BasketSummarized> summarizeBasket(@PathVariable String basketId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(basketService.summarizeBasket(basketId));
    }
}
