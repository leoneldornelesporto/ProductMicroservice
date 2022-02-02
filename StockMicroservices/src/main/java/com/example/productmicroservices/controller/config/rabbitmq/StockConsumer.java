package com.example.productmicroservices.controller.config.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constantes.RabbitmqConstantes;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import request.ProductUpdateRequest;

@Component
public class StockConsumer {

    @RabbitListener(queues = RabbitmqConstantes.FILA_ESTOQUE)
    private void updateStock(String message) throws JsonProcessingException, InterruptedException {
        ProductUpdateRequest productUpdateRequest = new ObjectMapper().readValue(message, ProductUpdateRequest.class);

        System.out.println(productUpdateRequest.getName());
        System.out.println(productUpdateRequest.getColor());
        System.out.println("------------------------------------");
        Thread.sleep(30000);
    }
}