package com.example.productmicroservices.service;

public interface RabbitmqService {
    void enviaMensagem(String nomeFila, Object mensagem);
}
