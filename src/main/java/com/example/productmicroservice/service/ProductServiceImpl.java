package com.example.productmicroservice.service;

import com.example.productmicroservice.controller.request.ProductRequest;
import com.example.productmicroservice.domain.Product;
import com.example.productmicroservice.exception.ResourceNotFoundException;
import com.example.productmicroservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

@Service
public class ProductServiceImpl implements ProductService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Collection<Product> findAll(){
        LOGGER.info("Find all products");
        List<Product> allProduct = productRepository.findAll();
        if (allProduct.isEmpty()){
            throw new ResourceNotFoundException("Product's not found");
        }
        return allProduct;
    }

    @Override
    public Product findByUuid(String uuid) {
        LOGGER.info("Find product by uuid: {}",uuid);

        Product product = productRepository.findByUuid(uuid);

        if (isNull(product)){
            throw new ResourceNotFoundException("Product not found for uuid: "+uuid);
        }
        return product;
    }

    @Override
    public void delete(String uuid){
        Product product = productRepository.findByUuid(uuid);
        if (isNull(product)){
            throw new ResourceNotFoundException("Product not found for uuid: "+uuid);
        }
        productRepository.delete(product);
    }

    @Override
    public Product update(String uuid, ProductRequest productRequest) {
        Product product = productRepository.findByUuid(uuid);

        if (isNull(product)){
            throw new ResourceNotFoundException("Product not found for uuid: "+uuid);
        }

        ofNullable(productRequest.getName()).ifPresent(product::setName);
        ofNullable(productRequest.getColor()).ifPresent(product::setColor);

        return productRepository.save(product);
    }

    @Override
    public Product save(ProductRequest productRequest) {
        Product product = new Product();
        ofNullable(productRequest.getName()).ifPresent(product::setName);
        ofNullable(productRequest.getColor()).ifPresent(product::setColor);
        product.setActive(true);
        return productRepository.save(product);
    }
}
