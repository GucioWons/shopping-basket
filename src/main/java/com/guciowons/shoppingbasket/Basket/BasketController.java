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
    public ResponseEntity<String> createBasket(@RequestBody Basket basket){
        basketService.createBasket(basket);
        return new ResponseEntity<>("Done", HttpStatus.CREATED);
    }

    @PutMapping(value = "/{basketId}/{productId}/{quantity}")
    public ResponseEntity<String> addProductToBasket(@PathVariable int basketId,@PathVariable int productId, @PathVariable int quantity){
        try{
            basketService.addProductToBasket(basketId, productId, quantity);
            return new ResponseEntity<>("Done", HttpStatus.ACCEPTED);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @DeleteMapping(value = "/{id}/{quantity}")
//    public ResponseEntity<String> removeProductFromBasket(@PathVariable int id, @PathVariable int quantity){
//        try{
//            basketService.removeProductFromBasket(id, quantity);
//            return new ResponseEntity<>("Done", HttpStatus.ACCEPTED);
//        }catch(IllegalArgumentException e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<BasketSummarized> summarizeBasket(){
//        return new ResponseEntity<>(basketService.summarizeBasket(), HttpStatus.ACCEPTED);
//    }
}
