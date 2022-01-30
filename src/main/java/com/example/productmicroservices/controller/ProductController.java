package com.example.productmicroservices.controller;

import com.example.productmicroservices.controller.converter.ProductResponseConverter;
import com.example.productmicroservices.controller.request.ProductRequest;
import com.example.productmicroservices.controller.request.ProductUpdateRequest;
import com.example.productmicroservices.controller.response.ProductResponse;
import com.example.productmicroservices.domain.Product;
import com.example.productmicroservices.service.ProductService;
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
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductResponseConverter productResponseConverter;

    @PostMapping
    public ResponseEntity<? extends ProductResponse> save(@RequestBody @Validated ProductRequest request){
        LOGGER.info("stage=init method=ProductController.save message=Save Product Request={}", request.toString());
        Product product = productService.save(request);
        ProductResponse response = productResponseConverter.fromEntity(product);
        LOGGER.info("stage=end method=ProductController.save message=Save Product Response={}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Transactional
    public ResponseEntity<? extends ProductResponse> update(@PathVariable String uuid,
                                                            @RequestBody @Validated ProductUpdateRequest request
    ){
        LOGGER.info("stage=init method=ProductController.update message=Update Product Request={}", request.toString());
        Product product = productService.update(uuid,request);
        ProductResponse response = productResponseConverter.fromEntity(product);
        LOGGER.info("stage=end method=ProductController.update message=Save Product Response={}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<? extends ProductResponse> delete(@PathVariable String uuid){
        productService.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<? extends Collection<ProductResponse>> findAllProducts(){
        LOGGER.info("stage=init method=ProductController.findAllProducts message=Find All Products Product");
        Collection<ProductResponse> productResponses = ProductResponse.converter(productService.findAll());
        LOGGER.info("stage=end  method=ProductController.findAllProducts message=Find All Products Product Response={}", productResponses.toString());
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<? extends ProductResponse> findProductByUuid(@PathVariable String uuid){
        LOGGER.info("stage=init method=ProductController.findProductByUuid message=Find Product By Uuid");
        Product product = productService.findByUuid(uuid);
        ProductResponse response = productResponseConverter.fromEntity(product);
        LOGGER.info("stage=end method=ProductController.findProductByUuid message=Find Product By Uuid", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}