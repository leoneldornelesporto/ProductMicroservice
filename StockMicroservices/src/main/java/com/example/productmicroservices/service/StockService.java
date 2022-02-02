package com.example.productmicroservices.service;

import com.example.productmicroservices.controller.request.StockRequest;
import com.example.productmicroservices.controller.request.StockUpdateRequest;
import com.example.productmicroservices.domain.StockEntity;

import java.util.Collection;

public interface StockService {
    Collection<StockEntity> findAll();
    StockEntity findByUuid(String uuid);
    void delete(String uuid);
    StockEntity update(String uuid, StockUpdateRequest request);
    StockEntity save(StockRequest stockRequest);
}
