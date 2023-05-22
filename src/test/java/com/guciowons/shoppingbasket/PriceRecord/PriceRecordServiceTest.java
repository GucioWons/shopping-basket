package com.guciowons.shoppingbasket.PriceRecord;

import com.guciowons.shoppingbasket.Product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PriceRecordServiceTest {

    @Mock
    private PriceRecordRepository priceRecordRepository;

    @Captor
    private ArgumentCaptor<PriceRecord> priceRecordCaptor;
    private PriceRecordService underTest;

    @BeforeEach
    void setUp(){
        underTest = new PriceRecordService(priceRecordRepository);
    }

    @Test
    void createPriceRecord() {
        Product product = new Product(
                1,
                "Product",
                BigDecimal.valueOf(10),
                "Description");
        product.setId("first");

        underTest.createPriceRecord(product);

        verify(priceRecordRepository).save(priceRecordCaptor.capture());

        PriceRecord capturedPriceRecord = priceRecordCaptor.getValue();
        assertThat(capturedPriceRecord).satisfies(priceRecord -> {
            assertThat(priceRecord.getProductId()).isEqualTo(product.getId());
            assertThat(priceRecord.getPrice()).isEqualTo(product.getPrice());
        });
    }

    @Test
    void getPriceRecords() {
        underTest.getPriceRecords();

        verify(priceRecordRepository).findAll();
    }

    @Test
    void getPriceRecordsBetweenDateTime() {
        LocalDateTime after = LocalDateTime.of(2000, 1, 1, 12, 0);
        LocalDateTime before = LocalDateTime.of(2000, 3, 1, 12, 0);

        underTest.getPriceRecordsBetweenDateTime(after, before);

        verify(priceRecordRepository).findByDateTimeBetween(after, before);
    }

    @Test
    void getPriceRecordsByProduct() {
        underTest.getPriceRecordsByProduct("first");

        verify(priceRecordRepository).findAllByProductId("first");
    }

    @Test
    void getPriceRecordsBetweenDateTimeByProduct() {
        LocalDateTime after = LocalDateTime.of(2000, 1, 1, 12, 0);
        LocalDateTime before = LocalDateTime.of(2000, 3, 1, 12, 0);

        underTest.getPriceRecordsBetweenDateTimeByProduct("first", after, before);

        verify(priceRecordRepository).findAllByProductIdAndDateTimeBetween("first", after, before);
    }
}