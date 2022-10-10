package com.guciowons.shoppingbasket.Product;

import com.guciowons.shoppingbasket.PriceRecord.PriceRecord;
import com.guciowons.shoppingbasket.PriceRecord.PriceRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransProductService{
    private final ProductRepository productRepository;
    private final PriceRecordRepository priceRecordRepository;

    public TransProductService(ProductRepository productRepository, PriceRecordRepository priceRecordRepository) {
        this.productRepository = productRepository;
        this.priceRecordRepository = priceRecordRepository;
    }
    @Transactional
    public void insertProduct(Product externalProduct){
        productRepository.save(externalProduct);
        priceRecordRepository.save(new PriceRecord(externalProduct.getId(), externalProduct.getPrice(), LocalDateTime.now()));
    }

    @Transactional
    public void updateProduct(Product databaseProduct, Product externalProduct){
        externalProduct.setId(databaseProduct.getId());
        priceRecordRepository.save(new PriceRecord(externalProduct.getId(), externalProduct.getPrice(), LocalDateTime.now()));
        productRepository.save(externalProduct);
    }
}
