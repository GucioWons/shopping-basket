package com.guciowons.shoppingbasket.Basket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BasketControllerTest {
    @Mock
    private BasketService basketService;
    private BasketController underTest;

    @BeforeEach
    void setUp(){
        underTest = new BasketController(basketService);
    }

    @Test
    void createBasket() {
        ResponseEntity<Basket> result = underTest.createBasket();

        verify(basketService).createBasket();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void addProductToBasket() {
        ResponseEntity<Basket> result = underTest.addProductToBasket("first", "product", 1);

        verify(basketService).addProductToBasket("first", "product", 1);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void removeProductFromBasket() {
        ResponseEntity<Basket> result = underTest.removeProductFromBasket("first", "product", 1);

        verify(basketService).removeProductFromBasket("first", "product", 1);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void summarizeBasket() {
        ResponseEntity<BasketSummarized> result = underTest.summarizeBasket("first");

        verify(basketService).summarizeBasket("first");

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}