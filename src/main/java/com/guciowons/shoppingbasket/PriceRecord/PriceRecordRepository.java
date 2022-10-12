package com.guciowons.shoppingbasket.PriceRecord;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRecordRepository extends MongoRepository<PriceRecord, BigDecimal> {
    List<PriceRecord> findByDateTimeBetween(LocalDateTime from, LocalDateTime to);
    List<PriceRecord> findAllByProductId(String productId);
    List<PriceRecord> findAllByProductIdAndDateTimeBetween(String productId, LocalDateTime from, LocalDateTime to);
}
