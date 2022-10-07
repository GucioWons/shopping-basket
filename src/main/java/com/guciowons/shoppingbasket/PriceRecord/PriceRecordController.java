package com.guciowons.shoppingbasket.PriceRecord;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
