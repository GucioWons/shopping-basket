package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.Product;

import java.util.HashMap;

public class Basket {
    private HashMap<Product, Integer> content = new HashMap<>();

    public Basket() {
    }

    public HashMap<Product, Integer> getContent() {
        return content;
    }

    public void setContent(HashMap<Product, Integer> content) {
        this.content = content;
    }

    public void addProduct(Product product, int quantity){
        if(!content.containsKey(product)){
            content.put(product, quantity);
        }else{
            int current = content.get(product);
            content.put(product, current+quantity);
        }
    }

    public void removeProduct(Product product, int quantity){
        int current = content.get(product);
        if(current <= quantity){
            content.remove(product);
        }else{
            content.put(product, current-quantity);
        }
    }
}
