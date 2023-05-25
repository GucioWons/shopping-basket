package com.guciowons.shoppingbasket.PriceRecord;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class PriceRecordRepositoryTest {

    @Autowired
    private PriceRecordRepository underTest;

    @BeforeEach
    void setUp(){
        underTest.save(new PriceRecord("first", new BigDecimal(10), LocalDateTime.of(2000, 2, 1, 12, 0)));
        underTest.save(new PriceRecord("second", new BigDecimal(20), LocalDateTime.of(2000, 2, 1, 12, 0)));
        underTest.save(new PriceRecord("first", new BigDecimal(10), LocalDateTime.of(2001, 1, 2, 12, 0)));
    }

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void findByDateTimeBetween() {
        LocalDateTime after = LocalDateTime.of(2000, 1, 1, 12, 0);
        LocalDateTime before = LocalDateTime.of(2000, 3, 1, 12, 0);

        List<PriceRecord> result = underTest.findByDateTimeBetween(after, before);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getDateTime()).isBetween(after, before);
        assertThat(result.get(1).getDateTime()).isBetween(after, before);
    }

    @Test
    void findAllByProductId() {
        List<PriceRecord> result = underTest.findAllByProductId("first");

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getProductId()).isEqualTo("first");
        assertThat(result.get(1).getProductId()).isEqualTo("first");
    }

    @Test
    void findAllByProductIdAndDateTimeBetween() {
        LocalDateTime after = LocalDateTime.of(2000, 1, 1, 12, 0);
        LocalDateTime before = LocalDateTime.of(2000, 3, 1, 12, 0);

        List<PriceRecord> result = underTest.findAllByProductIdAndDateTimeBetween("first", after, before);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getProductId()).isEqualTo("first");
        assertThat(result.get(0).getDateTime()).isBetween(after, before);

    }
}