package com.guciowons.shoppingbasket.PriceRecord;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prices")
public class PriceRecordController {
    private final PriceRecordService priceRecordService;

    public PriceRecordController(PriceRecordService priceRecordService) {
        this.priceRecordService = priceRecordService;
    }

    @GetMapping
    public ResponseEntity<List<PriceRecord>> getPriceRecords(
            @RequestParam("product") Optional<String> productId,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> to){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dupa(productId, from, to));
    }
    private List<PriceRecord> dupa(Optional<String> productId, Optional<LocalDateTime> from, Optional<LocalDateTime> to){
        return productId
                .map(product -> getPricesWithProduct(product, from.orElse(null), to.orElse(null)))
                .orElseGet(() -> getPricesWithoutProduct(from.orElse(null), to.orElse(null)));
    }

    private List<PriceRecord> getPricesWithProduct(String productId, LocalDateTime from, LocalDateTime to){
        if(from != null && to != null){
            return priceRecordService.getPriceRecordsBetweenDateTimeByProduct(productId, from, to);
        }else{
            return priceRecordService.getPriceRecordsByProduct(productId);
        }
    }

    private List<PriceRecord> getPricesWithoutProduct(LocalDateTime from, LocalDateTime to){
        if(from != null && to != null){
            return priceRecordService.getPriceRecordsBetweenDateTime(from, to);
        }else{
            return priceRecordService.getPriceRecords();
        }
    }
}
