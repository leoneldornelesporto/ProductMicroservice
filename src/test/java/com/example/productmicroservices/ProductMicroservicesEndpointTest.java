package com.example.productmicroservices;

import com.example.productmicroservices.domain.Product;
import com.example.productmicroservices.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductMicroservicesEndpointTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getProductsByUuid(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity("/api/v1/product/PROD-95d98b0c-7bf9-4fe0-9319-1a59311bd75c", String.class);
        Assertions.assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void listProductShouldReturnStatusCode200(){
        List<Product> productList = asList(new Product("PROD-eff3d343-f076-43e5-99de-4907e624c379","Coca Cola","Red",Boolean.TRUE),
                new Product("PROD-eff3d343-f076-43e5-91de-4907e624c334","Fanta","Blue",Boolean.TRUE));
        BDDMockito.when(productRepository.findAll()).thenReturn(productList);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("/api/v1/product/", String.class);
        Assertions.assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
