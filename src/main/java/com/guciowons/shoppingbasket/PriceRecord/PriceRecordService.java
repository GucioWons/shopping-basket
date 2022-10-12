package com.guciowons.shoppingbasket.PriceRecord;

import com.guciowons.shoppingbasket.Product.Product;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceRecordService {
    private final PriceRecordRepository priceRecordRepository;

    public final boolean rollback = true;

    public PriceRecordService(PriceRecordRepository priceRecordRepository) {
        this.priceRecordRepository = priceRecordRepository;
    }

    public void createPriceRecord(Product product) throws Exception {
        if(rollback){
            throw new Exception();
        }
        priceRecordRepository.save(new PriceRecord(product.getId(), product.getPrice(), LocalDateTime.now()));
    }

    public List<PriceRecord> getPriceRecords() {
        return priceRecordRepository.findAll();
    }

    public List<PriceRecord> getPriceRecordsBetweenDateTime(LocalDateTime from, LocalDateTime to) {
        return priceRecordRepository.findByDateTimeBetween(from, to);
    }

    public List<PriceRecord> getPriceRecordsByProduct(String productId) {
        return priceRecordRepository.findAllByProductId(productId);
    }

    public List<PriceRecord> getPriceRecordsBetweenDateTimeByProduct(String productId, LocalDateTime from, LocalDateTime to) {
        return priceRecordRepository.findAllByProductIdAndDateTimeBetween(productId, from, to);
    }
}
