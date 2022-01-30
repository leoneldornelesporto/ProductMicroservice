package com.example.productmicroservices.service;

import com.example.productmicroservices.controller.request.ProductRequest;
import com.example.productmicroservices.controller.request.ProductUpdateRequest;
import com.example.productmicroservices.domain.Product;

import java.util.Collection;

public interface ProductService {
    Collection<Product> findAll();
    Product findByUuid(String uuid);
    void delete(String uuid);
    Product update(String uuid, ProductUpdateRequest request);
    Product save(ProductRequest productRequest);
}
