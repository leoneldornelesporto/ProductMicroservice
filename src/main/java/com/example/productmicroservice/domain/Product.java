package com.example.productmicroservice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
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
    private Boolean active;

    @PrePersist
    public void initializeUUID() {
        if (Objects.isNull(uuid)) {
            uuid = "PROD-" + UUID.randomUUID();
        }
    }
}
