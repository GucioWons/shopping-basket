package com.guciowons.shoppingbasket.Basket;

import feign.FeignException;
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
    public ResponseEntity addProductToBasket(@PathVariable int basketId, @PathVariable int productId, @PathVariable int quantity){
        try{
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(basketService.addProductToBasket(basketId, productId, quantity));
        }catch(IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }catch(FeignException e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to fetch product from external provider");
        }
    }

    @DeleteMapping(value = "/{basketId}/{productId}/{quantity}")
    public ResponseEntity<String> removeProductFromBasket(@PathVariable int basketId, @PathVariable int productId, @PathVariable int quantity){
        try{
            basketService.removeProductFromBasket(basketId, productId, quantity);
            return new ResponseEntity<>("Done", HttpStatus.ACCEPTED);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch(FeignException e){
            return new ResponseEntity<>("Unable to fetch product from external provider", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/{basketId}")
    public ResponseEntity summarizeBasket(@PathVariable int basketId){
        try{
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(basketService.summarizeBasket(basketId));
        }catch(IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }catch(FeignException e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to fetch product from external provider");
        }
    }
}
