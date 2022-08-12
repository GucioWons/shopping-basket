package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Product.ProductDao;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class BasketSummarizer {
    public BigDecimal countPrices(HashMap<Integer, Integer> basketProducts, ProductDao productDao){
        List<BasketSummarized.MultiProduct> products = productsMapToList(basketProducts, productDao);
        BigDecimal price = new BigDecimal("0");
        for(BasketSummarized.MultiProduct multiProduct : products){
            price = price.add(multiProduct.getProduct().getCost().multiply(new BigDecimal(multiProduct.getQuantity())));
        }
        return price;
    }

    public List<BasketSummarized.MultiProduct> productsMapToList(HashMap<Integer, Integer> basketProducts, ProductDao productDao){
        List<BasketSummarized.MultiProduct> products = new ArrayList<>();
        basketProducts.forEach((key, value) -> {
            productDao.findById(key).ifPresent(
                    product -> products.add(new BasketSummarized.MultiProduct(product, value))
            );
        });
        return products;
    }
}
