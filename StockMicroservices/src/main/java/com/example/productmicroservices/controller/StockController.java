package com.example.productmicroservices.controller;

import com.example.productmicroservices.controller.converter.ProductResponseConverter;
import com.example.productmicroservices.controller.request.StockRequest;
import com.example.productmicroservices.controller.request.StockUpdateRequest;
import com.example.productmicroservices.controller.response.ProductResponse;
import com.example.productmicroservices.domain.StockEntity;
import com.example.productmicroservices.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/product")
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductResponseConverter productResponseConverter;

    @PostMapping
    public ResponseEntity<? extends ProductResponse> save(@RequestBody @Validated StockRequest request){
        LOGGER.info("stage=init method=ProductController.save message=Save Product Request={}", request.toString());
        StockEntity stockEntity = stockService.save(request);
        ProductResponse response = productResponseConverter.fromEntity(stockEntity);
        LOGGER.info("stage=end method=ProductController.save message=Save Product Response={}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Transactional
    public ResponseEntity<? extends ProductResponse> update(@PathVariable String uuid,
                                                            @RequestBody @Validated StockUpdateRequest request
    ){
        LOGGER.info("stage=init method=ProductController.update message=Update Product Request={}", request.toString());
        StockEntity stockEntity = stockService.update(uuid,request);
        ProductResponse response = productResponseConverter.fromEntity(stockEntity);
        LOGGER.info("stage=end method=ProductController.update message=Save Product Response={}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<? extends ProductResponse> delete(@PathVariable String uuid){
        stockService.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<? extends Collection<ProductResponse>> findAllProducts(){
        LOGGER.info("stage=init method=ProductController.findAllProducts message=Find All Products Product");
        Collection<ProductResponse> productResponses = ProductResponse.converter(stockService.findAll());
        LOGGER.info("stage=end  method=ProductController.findAllProducts message=Find All Products Product Response={}", productResponses.toString());
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<? extends ProductResponse> findProductByUuid(@PathVariable String uuid){
        LOGGER.info("stage=init method=ProductController.findProductByUuid message=Find Product By Uuid");
        StockEntity stockEntity = stockService.findByUuid(uuid);
        ProductResponse response = productResponseConverter.fromEntity(stockEntity);
        LOGGER.info("stage=end method=ProductController.findProductByUuid message=Find Product By Uuid", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}