package com.example.productmicroservices.service;

import com.example.productmicroservices.controller.request.StockRequest;
import com.example.productmicroservices.controller.request.StockUpdateRequest;
import com.example.productmicroservices.domain.StockEntity;
import com.example.productmicroservices.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);
    private StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Collection<StockEntity> findAll() {
        LOGGER.info("Find all products");
        List<StockEntity> allStockEntity = stockRepository.findAll();
        if (allStockEntity.isEmpty()){
            throw new ResourceNotFoundException("Product's not found");
        }
        return allStockEntity;
    }

    @Override
    public StockEntity findByUuid(String uuid) {
        LOGGER.info("Find product by uuid: {}",uuid);

        Optional<StockEntity> product = stockRepository.findByUuid(uuid);

        if (!product.isPresent()){
            throw new ResourceNotFoundException("Product not found for uuid: "+uuid);
        }
        return product.get();
    }

    @Override
    public void delete(String uuid) {
        Optional<StockEntity> product = stockRepository.findByUuid(uuid);
        if (!product.isPresent()){
            throw new ResourceNotFoundException("Product not found for uuid: "+uuid);
        }
        stockRepository.delete(product.get());
    }

    @Override
    public StockEntity update(String uuid, StockUpdateRequest request) {
        Optional<StockEntity> productUpdate = stockRepository.findByUuid(uuid);

        if (!productUpdate.isPresent()){
            throw new ResourceNotFoundException("Product not found for uuid: "+uuid);
        }

        StockEntity stockEntity = stockRepository.findById(productUpdate.get().getId()).get();
        stockEntity.setName(request.getName());
        stockEntity.setColor(request.getColor());
        stockEntity.setActive(request.getActive());

        return stockEntity;
    }

    @Override
    public StockEntity save(StockRequest stockRequest) {
        StockEntity stockEntity = new StockEntity(stockRequest);
        return stockRepository.save(stockEntity);
    }
}
