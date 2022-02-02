package com.example.productmicroservices.controller.response;

import com.example.productmicroservices.domain.StockEntity;
import lombok.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductResponse {
    private String uuid;
    private String name;
    private String color;
    private Boolean active;

    public ProductResponse() {
    }

    public ProductResponse(StockEntity stockEntity) {
        this.uuid = stockEntity.getUuid();
        this.name = stockEntity.getName();
        this.color = stockEntity.getColor();
        this.active = stockEntity.getActive();
    }

    public static Collection<ProductResponse> converter(Collection<StockEntity> all) {
        return all.stream().map(ProductResponse::new).collect(Collectors.toList());
    }
}
