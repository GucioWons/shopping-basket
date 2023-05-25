package com.guciowons.shoppingbasket.PriceRecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PriceRecordTest {
    private PriceRecord underTest;

    @BeforeEach
    void setUp(){
        underTest = new PriceRecord("product", BigDecimal.valueOf(20), LocalDateTime.MIN);
    }

    @Test
    void getId_setId() {
        underTest.setId("first");

        assertThat(underTest.getId()).isEqualTo("first");
    }

    @Test
    void getProductId_setProductId() {
        underTest.setProductId("product");

        assertThat(underTest.getProductId()).isEqualTo("product");
    }

    @Test
    void getPrice_setPrice() {
        underTest.setPrice(BigDecimal.valueOf(10));

        assertThat(underTest.getPrice()).isEqualTo(BigDecimal.valueOf(10));
    }

    @Test
    void getDateTime_setDateTime() {
        underTest.setDateTime(LocalDateTime.MAX);

        assertThat(underTest.getDateTime()).isEqualTo(LocalDateTime.MAX);
    }
}