package com.example.productmicroservices.service;

import com.example.productmicroservices.controller.request.ProductRequest;
import com.example.productmicroservices.controller.request.ProductUpdateRequest;
import com.example.productmicroservices.domain.Product;
import com.example.productmicroservices.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Collection<Product> findAll() {
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

        Optional<Product> product = productRepository.findByUuid(uuid);

        if (!product.isPresent()){
            throw new ResourceNotFoundException("Product not found for uuid: "+uuid);
        }
        return product.get();
    }

    @Override
    public void delete(String uuid) {
        Optional<Product> product = productRepository.findByUuid(uuid);
        if (!product.isPresent()){
            throw new ResourceNotFoundException("Product not found for uuid: "+uuid);
        }
        productRepository.delete(product.get());
    }

    @Override
    public Product update(String uuid, ProductUpdateRequest request) {
        Optional<Product> productUpdate = productRepository.findByUuid(uuid);

        if (!productUpdate.isPresent()){
            throw new ResourceNotFoundException("Product not found for uuid: "+uuid);
        }

        Product product = productRepository.findById(productUpdate.get().getId()).get();
        product.setName(request.getName());
        product.setColor(request.getColor());
        product.setActive(request.getActive());

        return product;
    }

    @Override
    public Product save(ProductRequest productRequest) {
        Product product = new Product(productRequest);
        return productRepository.save(product);
    }
}
