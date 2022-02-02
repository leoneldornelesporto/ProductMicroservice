package com.example.productmicroservices.controller.converter;

import com.example.productmicroservices.controller.response.ProductResponse;
import com.example.productmicroservices.domain.StockEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseConverter {

    public ProductResponse fromEntity(StockEntity stockEntity) {
        ProductResponse response = new ProductResponse();

        if (stockEntity != null){
            response.setUuid(stockEntity.getUuid());
            response.setName(stockEntity.getName());
            response.setColor(stockEntity.getColor());
            response.setActive(stockEntity.getActive());
        }
        return response;
    }
}