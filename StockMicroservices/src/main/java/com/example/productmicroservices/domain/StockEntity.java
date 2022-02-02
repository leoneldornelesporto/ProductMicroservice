package com.example.productmicroservices.domain;

import com.example.productmicroservices.controller.request.StockRequest;
import com.example.productmicroservices.controller.request.StockUpdateRequest;
import lombok.*;
import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockEntity extends AbstractEntity{
    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Boolean active = Boolean.TRUE;

    public StockEntity(StockRequest stockRequest) {
        this.name = stockRequest.getName();
        this.color = stockRequest.getColor();
    }

    public StockEntity(StockUpdateRequest request) {
        this.name = request.getName();
        this.color = request.getColor();
        this.active = request.getActive();
    }

    @PrePersist
    public void initializeUUID() {
        if (Objects.isNull(uuid)) {
            uuid = "PROD-" + UUID.randomUUID();
        }
    }

    public StockEntity setProductUpdateRequest(StockUpdateRequest request) {
        return new StockEntity(request);
    }
}
