package com.example.productmicroservice.repository;

import com.example.productmicroservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByUuid(String uuid);
}
