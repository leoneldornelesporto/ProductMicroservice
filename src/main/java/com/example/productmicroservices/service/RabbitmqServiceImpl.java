package com.example.productmicroservices.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqServiceImpl implements RabbitmqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void enviaMensagem(String nomeFila, Object mensagem) {
        this.rabbitTemplate.convertAndSend(nomeFila,mensagem);
    }
}
