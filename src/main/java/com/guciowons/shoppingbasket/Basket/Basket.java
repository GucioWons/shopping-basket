package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Exception.NoProductInBasketException;

import java.util.HashMap;

public class Basket {
    private final int id;
    private HashMap<Integer, Integer> content = new HashMap<>();

    public Basket(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public HashMap<Integer, Integer> getContent() {
        return content;
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
