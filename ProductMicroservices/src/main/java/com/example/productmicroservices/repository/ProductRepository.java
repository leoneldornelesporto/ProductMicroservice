package com.example.productmicroservices.repository;

import com.example.productmicroservices.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByUuid(String uuid);
}
