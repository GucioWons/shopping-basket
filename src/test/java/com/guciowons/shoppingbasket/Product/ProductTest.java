package com.guciowons.shoppingbasket.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductTest {
    private Product underTest;

    @BeforeEach
    void setUp(){
        underTest = new Product(1000, "abcd", new BigDecimal(1), "qwertyuiop");
    }

    @Test
    void setId_getId(){
        underTest.setId("123456");
        assertThat(underTest.getId()).isEqualTo("123456");
    }

    @Test
    void setId_getId_wrong() {
        assertThat(underTest.getId()).isNull();
    }

    @Test
    void setExternalId_getExternalId() {
        underTest.setExternalId(1);
        assertThat(underTest.getExternalId()).isEqualTo(1);
    }

    @Test
    void setTitle_getTitle() {
        underTest.setTitle("Test product");
        assertThat(underTest.getTitle()).isEqualTo("Test product");
    }

    @Test
    void setPrice_getPrice() {
        BigDecimal price = new BigDecimal(20);
        underTest.setPrice(price);
        assertThat(underTest.getPrice()).isEqualTo(price);
    }

    @Test
    void setDescription_getDescription() {
        underTest.setDescription("Test product description");
        assertThat(underTest.getDescription()).isEqualTo("Test product description");
    }

    @Test
    void equalsAndHashcodeShouldBeReflexive() {
        Product product = new Product(1000, "abcd", new BigDecimal(1), "qwertyuiop");
        assertThat(underTest.hashCode()).isEqualTo(product.hashCode());
        assertThat(underTest.equals(product)).isTrue();
    }
}
