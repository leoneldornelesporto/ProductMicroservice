package com.example.productmicroservices.domain;

import com.example.productmicroservices.controller.request.ProductRequest;
import com.example.productmicroservices.controller.request.ProductUpdateRequest;
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
public class Product extends AbstractEntity{
    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Boolean active = Boolean.TRUE;

    public Product(ProductRequest productRequest) {
        this.name = productRequest.getName();
        this.color = productRequest.getColor();
    }

    public Product(ProductUpdateRequest request) {
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

    public Product setProductUpdateRequest(ProductUpdateRequest request) {
        return new Product(request);
    }
}
