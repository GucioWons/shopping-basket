package com.guciowons.shoppingbasket.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    private ProductController underTest;

    @BeforeEach
    void setUp(){
        underTest = new ProductController(productService);
    }

    @Test
    void getProducts() {
        List<Product> products = List.of(
                new Product(1, "Product", BigDecimal.valueOf(20), "Description")
        );
        when(productService.getProducts()).thenReturn(products);

        ResponseEntity<List<Product>> result = underTest.getProducts();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).contains(products.get(0));
    }

    @Test
    void getProducts_empty(){
        when(productService.getProducts()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Product>> result = underTest.getProducts();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEmpty();
    }
}