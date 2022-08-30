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
    public ResponseEntity<Object> addProductToBasket(@PathVariable int basketId, @PathVariable int productId, @PathVariable int quantity){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(basketService.addProductToBasket(basketId, productId, quantity));
    }

    @DeleteMapping(value = "/{basketId}/{productId}/{quantity}")
    public ResponseEntity<String> removeProductFromBasket(@PathVariable int basketId, @PathVariable int productId, @PathVariable int quantity){
        basketService.removeProductFromBasket(basketId, productId, quantity);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    @GetMapping(value="/{basketId}")
    public ResponseEntity<Object> summarizeBasket(@PathVariable int basketId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(basketService.summarizeBasket(basketId));
    }
}
