package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Exception.NoProductInBasketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BasketTest {
    private Basket underTest;

    @BeforeEach
    void setUp(){
        underTest = new Basket();
    }

    @Test
    void getId_setId() {
        underTest.setId("first");

        assertThat(underTest.getId()).isEqualTo("first");
    }

    @Test
    void getContent_setContent() {
        HashMap<String, Integer> content = new HashMap<>();
        content.put("product", 1);

        underTest.setContent(content);

        assertThat(underTest.getContent()).isEqualTo(content);
    }

    @Test
    void addProduct() {
        underTest.addProduct("product", 1);

        assertThat(underTest.getContent()).containsEntry("product", 1);
    }

    @Test
    void addProduct_inBasket(){
        underTest.addProduct("product", 1);

        underTest.addProduct("product", 2);

        assertThat(underTest.getContent()).containsEntry("product", 3);
    }

    @Test
    void removeProduct() {
        underTest.addProduct("product", 1);

        underTest.removeProduct("product", 1);

        assertThat(underTest.getContent()).doesNotContainKey("product");
    }

    @Test
    void removeProduct_reduce(){
        underTest.addProduct("product", 3);

        underTest.removeProduct("product", 1);

        assertThat(underTest.getContent()).containsEntry("product", 2);
    }

    @Test
    void removeProduct_notInBasket(){
        NoProductInBasketException exception = assertThrows(NoProductInBasketException.class,
                () -> underTest.removeProduct("product", 1));
        assertThat(exception.getMessage()).isEqualTo("No such product in the basket");
    }
}