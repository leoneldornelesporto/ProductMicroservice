package com.example.productmicroservices.controller.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    private String token;
    private String tipo;
}
