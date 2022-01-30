package com.example.productmicroservices.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {
    private String name;
    private String color;
    private Boolean active;
}
