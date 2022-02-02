package com.example.productmicroservices.repository;

import com.example.productmicroservices.domain.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity,Long> {
    Optional<StockEntity> findByUuid(String uuid);
}
