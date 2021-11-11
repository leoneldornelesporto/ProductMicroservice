package com.example.productmicroservice.service;

import com.example.productmicroservice.domain.Product;
import com.example.productmicroservice.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImplTest {

    private ProductServiceImpl service;
    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        service = new ProductServiceImpl(productRepository);
    }

    @Test
    void deveriaRetornarTodosOsProdutos(){
        Product product = product();

        Mockito.when(service.findByUuid(Mockito.any()))
                .thenReturn(product);

        service.findByUuid("PROD-56756JJHFG5");

        Assertions.assertEquals("Sapato",product.getName());
        Assertions.assertEquals("Rosa",product.getColor());
        Assertions.assertTrue(product.getActive());
    }

    @Test
    void deveriaDeletarProduct(){
        Product product = product();

        Mockito.when(service.findByUuid(Mockito.any()))
                .thenReturn(product);

        service.findByUuid("PROD-56756JJHFG5");

        Assertions.assertEquals("Sapato",product.getName());
        Assertions.assertEquals("Rosa",product.getColor());
        Assertions.assertTrue(product.getActive());
    }

    @Test
    void deveriaRetornarProdutosPorUuid(){
        List<Product> productCollection = listaDeProdutos();

        Mockito.when(service.findAll())
                .thenReturn(productCollection);

        service.findAll();

        Product product = productCollection.get(0);
        Assert.assertTrue(product.getActive());
    }

    private List<Product> listaDeProdutos(){
        List<Product> productCollection = new ArrayList<>();
        Product primeiroProduto = new Product("PROD-56756JJHFG5","Sapato","Rosa",Boolean.TRUE);
        productCollection.add(primeiroProduto);
        Product segundoProduto = new Product("PROD-JHGJ565467796","Tênis","Preto",Boolean.TRUE);
        productCollection.add(segundoProduto);
        return productCollection;
    }

    private Product product(){
        Product primeiroProduto = new Product("PROD-56756JJHFG5","Sapato","Rosa",Boolean.TRUE);
        return primeiroProduto;
    }
}
