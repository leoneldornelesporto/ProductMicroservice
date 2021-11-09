package com.example.productmicroservice.controller.response;

import com.example.productmicroservice.domain.Product;
import lombok.Getter;
import lombok.Setter;

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

    public ProductResponse(Product product) {
        this.uuid = product.getUuid();
        this.name = product.getName();
        this.color = product.getColor();
        this.active = product.getActive();
    }

    public static Collection<ProductResponse> converter(Collection<Product> all) {
        return all.stream().map(ProductResponse::new).collect(Collectors.toList());
    }
}
