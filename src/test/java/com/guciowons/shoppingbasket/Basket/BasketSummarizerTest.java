package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Exception.NoProductException;
import com.guciowons.shoppingbasket.Product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BasketSummarizerTest {

    private BasketSummarizer underTest;

    @BeforeEach
    void setUp(){
        underTest = new BasketSummarizer();
    }

    @Test
    void summarizeBasket_withOneProduct() {
        HashMap<String, Integer> basketProducts = new HashMap<>();
        basketProducts.put("first", 1);
        List<Product> allProducts = new ArrayList<>();
        Product product = new Product(1, "Product", BigDecimal.valueOf(20), "Description");
        product.setId("first");
        allProducts.add(product);

        BasketSummarized result = underTest.summarizeBasket(basketProducts, allProducts);

        assertThat(result.getCost()).isEqualTo(BigDecimal.valueOf(20));
        assertThat(result.getProducts().get(0).getSummarizedCost()).isEqualTo(BigDecimal.valueOf(20));
        assertThat(result.getProducts().get(0).getProduct()).isEqualTo(product);
        assertThat(result.getProducts().get(0).getQuantity()).isEqualTo(1);
    }

    @Test
    void summarizeBasket_withMultipleProducts() {
        HashMap<String, Integer> basketProducts = new HashMap<>();
        basketProducts.put("first", 2);
        List<Product> allProducts = new ArrayList<>();
        Product product = new Product(1, "Product", BigDecimal.valueOf(20), "Description");
        product.setId("first");
        allProducts.add(product);

        BasketSummarized result = underTest.summarizeBasket(basketProducts, allProducts);

        assertThat(result.getCost()).isEqualTo(BigDecimal.valueOf(40));
        assertThat(result.getProducts().get(0).getSummarizedCost()).isEqualTo(BigDecimal.valueOf(40));
        assertThat(result.getProducts().get(0).getProduct()).isEqualTo(product);
        assertThat(result.getProducts().get(0).getQuantity()).isEqualTo(2);
    }

    @Test
    void summarizeBasket_withDifferentProducts() {
        HashMap<String, Integer> basketProducts = new HashMap<>();
        basketProducts.put("first", 1);
        basketProducts.put("second", 1);
        List<Product> allProducts = new ArrayList<>();
        Product product = new Product(1, "Product", BigDecimal.valueOf(20), "Description");
        Product product2 = new Product(2, "Product2", BigDecimal.valueOf(10), "Description2");
        product.setId("first");
        product2.setId("second");
        allProducts.add(product);
        allProducts.add(product2);

        BasketSummarized result = underTest.summarizeBasket(basketProducts, allProducts);

        assertThat(result.getCost()).isEqualTo(BigDecimal.valueOf(30));
        assertThat(result.getProducts().get(0).getSummarizedCost()).isEqualTo(BigDecimal.valueOf(20));
        assertThat(result.getProducts().get(0).getProduct()).isEqualTo(product);
        assertThat(result.getProducts().get(0).getQuantity()).isEqualTo(1);

        assertThat(result.getProducts().get(1).getSummarizedCost()).isEqualTo(BigDecimal.valueOf(10));
        assertThat(result.getProducts().get(1).getProduct()).isEqualTo(product2);
        assertThat(result.getProducts().get(1).getQuantity()).isEqualTo(1);
    }

    @Test
    void summarizeBasket_empty(){
        HashMap<String, Integer> basketProducts = new HashMap<>();
        List<Product> allProducts = new ArrayList<>();

        BasketSummarized result = underTest.summarizeBasket(basketProducts, allProducts);

        assertThat(result.getCost()).isEqualTo(BigDecimal.valueOf(0));
        assertThat(result.getProducts()).isEmpty();
    }

    @Test
    void summarizeBasket_productNotExists(){
        HashMap<String, Integer> basketProducts = new HashMap<>();
        basketProducts.put("first", 2);
        List<Product> allProducts = new ArrayList<>();

        NoProductException exception = assertThrows(NoProductException.class,
                () -> underTest.summarizeBasket(basketProducts, allProducts));

        assertThat(exception.getMessage()).isEqualTo("Basket contains nonexistent product!");
    }
}