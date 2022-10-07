package com.guciowons.shoppingbasket.PriceRecord;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pricerecords")
public class PriceRecordController {
    private final PriceRecordService priceRecordService;

    public PriceRecordController(PriceRecordService priceRecordService) {
        this.priceRecordService = priceRecordService;
    }

    @GetMapping
    public ResponseEntity<List<PriceRecord>> getPriceRecords(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(priceRecordService.getPriceRecords());
    }

    @GetMapping("/datetime")
    public ResponseEntity<List<PriceRecord>> getPriceRecordsBetweenDateTime(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(priceRecordService.getPriceRecordsBetweenDateTime(from, to));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<PriceRecord>> getPriceRecordsByProduct(@PathVariable String productId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(priceRecordService.getPriceRecordsByProduct(productId));
    }

    @GetMapping("/product/{productId}/datetime")
    public ResponseEntity<List<PriceRecord>> getPriceRecordsBetweenDateTimeByProduct(
            @PathVariable String productId,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(priceRecordService.getPriceRecordsBetweenDateTimeByProduct(productId, from, to));
    }
}
