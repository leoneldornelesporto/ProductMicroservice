package com.example.productmicroservices;

import com.example.productmicroservices.domain.Product;
import com.example.productmicroservices.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductMicroservicesRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersisData(){
        Product product = new Product("PROD-SADSAD21434","Coca cola","Red",Boolean.TRUE);
        this.productRepository.save(product);
        Assertions.assertThat(product.getId()).isNotNull();
        Assertions.assertThat(product.getUuid()).isEqualTo("PROD-SADSAD21434");
        Assertions.assertThat(product.getName()).isEqualTo("Coca cola");
    }
}
