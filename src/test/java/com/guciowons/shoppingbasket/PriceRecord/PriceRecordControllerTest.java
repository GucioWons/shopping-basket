package com.guciowons.shoppingbasket.PriceRecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PriceRecordControllerTest {

    @Mock
    private PriceRecordService priceRecordService;
    private PriceRecordController underTest;

    @BeforeEach
    void setUp(){
        underTest = new PriceRecordController(priceRecordService);
    }

    @Test
    void getAllPriceRecords() {
        ResponseEntity<List<PriceRecord>> result = underTest.getPriceRecords(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        );

        verify(priceRecordService).getPriceRecords();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getProductPriceRecords(){
        ResponseEntity<List<PriceRecord>> result = underTest.getPriceRecords(
                Optional.of("first"),
                Optional.empty(),
                Optional.empty()
        );

        verify(priceRecordService).getPriceRecordsByProduct("first");

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getPriceRecordsBetweenDate(){
        LocalDateTime after = LocalDateTime.of(2000, 1,1,0,0);
        LocalDateTime before = LocalDateTime.of(2001, 1, 1, 0, 0);
        ResponseEntity<List<PriceRecord>> result = underTest.getPriceRecords(
                Optional.empty(),
                Optional.of(after),
                Optional.of(before)
        );

        verify(priceRecordService).getPriceRecordsBetweenDateTime(after, before);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getProductPriceRecordsBetweenDate(){
        LocalDateTime after = LocalDateTime.of(2000, 1,1,0,0);
        LocalDateTime before = LocalDateTime.of(2001, 1, 1, 0, 0);
        ResponseEntity<List<PriceRecord>> result = underTest.getPriceRecords(
                Optional.of("first"),
                Optional.of(after),
                Optional.of(before)
        );

        verify(priceRecordService).getPriceRecordsBetweenDateTimeByProduct("first", after, before);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}