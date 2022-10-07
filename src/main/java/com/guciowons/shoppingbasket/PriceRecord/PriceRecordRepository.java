package com.guciowons.shoppingbasket.PriceRecord;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PriceRecordRepository extends MongoRepository<PriceRecord, BigDecimal> {
}
