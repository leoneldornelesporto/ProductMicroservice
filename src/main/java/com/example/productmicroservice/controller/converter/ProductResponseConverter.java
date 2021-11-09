package com.example.productmicroservice.controller.converter;

import com.example.productmicroservice.controller.response.ProductResponse;
import com.example.productmicroservice.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseConverter {

    public ProductResponse fromEntity(Product product) {
        ProductResponse response = new ProductResponse();

        if (product != null){
            response.setUuid(product.getUuid());
            response.setName(product.getName());
            response.setColor(product.getColor());
            response.setActive(product.getActive());
        }
        return response;
    }
}
