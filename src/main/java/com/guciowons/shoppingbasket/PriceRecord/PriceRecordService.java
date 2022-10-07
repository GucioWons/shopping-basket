package com.guciowons.shoppingbasket.PriceRecord;

import com.guciowons.shoppingbasket.Product.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceRecordService {
    private final PriceRecordRepository priceRecordRepository;

    public PriceRecordService(PriceRecordRepository priceRecordRepository) {
        this.priceRecordRepository = priceRecordRepository;
    }

    public void createPriceRecord(Product product){
        PriceRecord priceRecord = new PriceRecord(product.getId(), product.getPrice(), LocalDateTime.now());
        priceRecordRepository.save(priceRecord);
    }

    public List<PriceRecord> getPriceRecords() {
        return priceRecordRepository.findAll();
    }
}
