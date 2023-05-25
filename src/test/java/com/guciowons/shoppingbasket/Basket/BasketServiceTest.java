package com.guciowons.shoppingbasket.Basket;

import com.guciowons.shoppingbasket.Exception.NoBasketException;
import com.guciowons.shoppingbasket.Exception.NoProductException;
import com.guciowons.shoppingbasket.Product.Product;
import com.guciowons.shoppingbasket.Product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {
    private BasketService underTest;

    @Mock
    private BasketSummarizer basketSummarizer;
    @Mock
    private BasketRepository basketRepository;
    @Mock
    private ProductService productService;
    @Captor
    private ArgumentCaptor<Basket> basketCaptor;

    @BeforeEach
    void setUp(){
        underTest = new BasketService(basketSummarizer, basketRepository, productService);
    }

    @Test
    void createBasket() {
        underTest.createBasket();

        verify(basketRepository).save(basketCaptor.capture());
        Basket basket = basketCaptor.getValue();

        assertThat(basket).isNotNull();
    }

    @Test
    void addProductToBasket() {
        Basket basket = new Basket();
        basket.setId("first");
        basket.setContent(new HashMap<>());
        Product product = new Product(1, "Product", BigDecimal.valueOf(20), "Description");
        product.setId("product");

        when(basketRepository.findById("first")).thenReturn(Optional.of(basket));
        when(productService.getProductById("product")).thenReturn(Optional.of(product));
        when(basketRepository.save(any())).thenReturn(basket);
        Basket result = underTest.addProductToBasket("first", "product", 1);

        verify(basketRepository).findById("first");
        verify(productService).getProductById("product");
        verify(basketRepository).save(basket);

        assertThat(result.getContent()).containsEntry("product", 1);
    }

    @Test
    void addProductToBasket_noBasket(){
        when(basketRepository.findById("first")).thenReturn(Optional.empty());

        NoBasketException exception = assertThrows(NoBasketException.class,
                () -> underTest.addProductToBasket("first", "product", 1));

        assertThat(exception.getMessage()).isEqualTo("No such basket");
    }

    @Test
    void addProductToBasket_noProduct(){
        Basket basket = new Basket();
        basket.setId("first");
        basket.setContent(new HashMap<>());

        when(basketRepository.findById("first")).thenReturn(Optional.of(basket));

        NoProductException exception = assertThrows(NoProductException.class,
                () -> underTest.addProductToBasket("first", "product", 1));

        assertThat(exception.getMessage()).isEqualTo("No such product!");
    }

    @Test
    void removeProductFromBasket() {
        Basket basket = new Basket();
        basket.setId("first");
        HashMap<String, Integer> content = new HashMap<>();
        content.put("product", 1);
        basket.setContent(content);
        Product product = new Product(1, "Product", BigDecimal.valueOf(20), "Description");
        product.setId("product");

        when(basketRepository.findById("first")).thenReturn(Optional.of(basket));
        when(productService.getProductById("product")).thenReturn(Optional.of(product));
        when(basketRepository.save(any())).thenReturn(basket);
        Basket result = underTest.removeProductFromBasket("first", "product", 1);

        verify(basketRepository).findById(basket.getId());
        verify(productService).getProductById(product.getId());
        verify(basketRepository).save(basket);

        assertThat(result.getContent()).isEmpty();
    }

    @Test
    void removeProductFromBasket_noBasket(){
        when(basketRepository.findById("first")).thenReturn(Optional.empty());

        NoBasketException exception = assertThrows(NoBasketException.class,
                () -> underTest.removeProductFromBasket("first", "product", 1));

        assertThat(exception.getMessage()).isEqualTo("No such basket");
    }

    @Test
    void removeProductFromBasket_noProduct(){
        Basket basket = new Basket();
        basket.setId("first");
        basket.setContent(new HashMap<>());

        when(basketRepository.findById("first")).thenReturn(Optional.of(basket));

        NoProductException exception = assertThrows(NoProductException.class,
                () -> underTest.removeProductFromBasket("first", "product", 1));

        assertThat(exception.getMessage()).isEqualTo("No such product!");
    }

    @Test
    void summarizeBasket() {
        HashMap<String, Integer> content = new HashMap<>();
        content.put("product", 20);
        Basket basket = new Basket();
        basket.setId("first");
        basket.setContent(content);

        when(basketRepository.findById(basket.getId())).thenReturn(Optional.of(basket));
        when(basketSummarizer.summarizeBasket(eq(basket.getContent()), any())).thenReturn(new BasketSummarized(List.of(), BigDecimal.valueOf(0)));
        underTest.summarizeBasket(basket.getId());

        verify(basketRepository).findById(basket.getId());
        verify(basketSummarizer).summarizeBasket(eq(basket.getContent()), any());
    }

    @Test
    void summarizeBasket_notExist(){
        when(basketRepository.findById("first")).thenReturn(Optional.empty());

        NoBasketException exception = assertThrows(NoBasketException.class,
                () -> underTest.summarizeBasket("first"));

        assertThat(exception.getMessage()).isEqualTo("No such basket");
    }
}