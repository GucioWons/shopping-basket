package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Exception.NoProductInBasketException;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document
public class Basket {
    @Id
    private int id;
    private HashMap<Integer, Integer> content = new HashMap<>();

    @Version
    private Integer version;

    public Basket(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Integer, Integer> getContent() {
        return content;
    }

    public void setContent(HashMap<Integer, Integer> content) {
        this.content = content;
    }

    public void addProduct(Integer productId, int quantity){
        if(!content.containsKey(productId)){
            content.put(productId, quantity);
        }else{
            int current = content.get(productId);
            content.put(productId, current+quantity);
        }
    }

    public void removeProduct(Integer productId, int quantity) throws NoProductInBasketException {
        if(content.containsKey(productId)){
            removeIfContains(productId, quantity);
        }else {
            throw new NoProductInBasketException("No such product in the basket");
        }
    }

    private void removeIfContains(Integer productId, int quantity){
        int current = content.get(productId);
        if(current <= quantity){
            content.remove(productId);
        }else{
            content.put(productId, current-quantity);
        }
    }
}
