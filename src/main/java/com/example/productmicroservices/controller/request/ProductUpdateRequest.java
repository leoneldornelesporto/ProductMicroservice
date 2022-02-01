package com.example.productmicroservices.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest implements Serializable {
    private String name;
    private String color;
    private Boolean active;
}
