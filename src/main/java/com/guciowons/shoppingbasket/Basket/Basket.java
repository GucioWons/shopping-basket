package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Exception.NoProductInBasketException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document
public class Basket {
    @Id
    private String id;
    private HashMap<String, Integer> content = new HashMap<>();

    public Basket() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, Integer> getContent() {
        return content;
    }

    public void setContent(HashMap<String, Integer> content) {
        this.content = content;
    }

    public void addProduct(String productId, int quantity){
        if(!content.containsKey(productId)){
            content.put(productId, quantity);
        }else{
            int current = content.get(productId);
            content.put(productId, current+quantity);
        }
    }

    public void removeProduct(String productId, int quantity) throws NoProductInBasketException {
        if(content.containsKey(productId)){
            removeIfContains(productId, quantity);
        }else {
            throw new NoProductInBasketException("No such product in the basket");
        }
    }

    private void removeIfContains(String productId, int quantity){
        int current = content.get(productId);
        if(current <= quantity){
            content.remove(productId);
        }else{
            content.put(productId, current-quantity);
        }
    }
}
