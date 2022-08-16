package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Component
public class BasketSummarizer {
    public BasketSummarized summarizeBasket(HashMap<Integer, Integer> basketProducts, List<Product> allProducts){
        return new BasketSummarized(
                productsMapToList(basketProducts, allProducts),
                countPrices(basketProducts, allProducts));
    }

    private BigDecimal countPrices(HashMap<Integer, Integer> basketProducts, List<Product> allProducts){
        return basketProducts.entrySet().stream()
                .map(product -> findProductById(
                        allProducts, product.getKey()).getPrice().multiply(BigDecimal.valueOf(product.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<BasketSummarized.MultiProduct> productsMapToList(HashMap<Integer, Integer> basketProducts, List<Product> allProducts){
        return basketProducts.entrySet().stream()
                .map(product -> summarizeProduct(findProductById(allProducts, product.getKey()), product.getValue())).toList();
    }

    private Product findProductById(List<Product> allProducts, int id){
        for(Product product : allProducts){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }

    private BasketSummarized.MultiProduct summarizeProduct(Product product, int quantity){
        return new BasketSummarized.MultiProduct(product, quantity, product.getPrice().multiply(BigDecimal.valueOf(quantity)));
    }
}
