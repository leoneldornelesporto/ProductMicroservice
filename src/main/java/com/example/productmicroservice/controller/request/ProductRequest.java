package com.example.productmicroservice.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @Valid
    @NotNull(message = "invalid.product.name")
    private String name;

    @Valid
    @NotNull(message = "invalid.product.color")
    private String color;
}
