package com.example.productmicroservice.service;

import com.example.productmicroservice.controller.request.ProductRequest;
import com.example.productmicroservice.domain.Product;

import java.util.Collection;

public interface ProductService {
    Collection<Product> findAll();
    Product findByUuid(String uuid);
    void delete(String uuid);
    Product update(String uuid, ProductRequest productRequest);
    Product save(ProductRequest productRequest);
}
