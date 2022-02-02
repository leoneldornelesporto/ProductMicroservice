package com.example.productmicroservices;

import com.example.productmicroservices.domain.StockEntity;
import com.example.productmicroservices.repository.StockRepository;
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
public class StockEntityMicroservicesRepositoryTest {

    @Autowired
    private StockRepository stockRepository;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersisData(){
        StockEntity stockEntity = new StockEntity("PROD-SADSAD21434","Coca cola","Red",Boolean.TRUE);
        this.stockRepository.save(stockEntity);
        Assertions.assertThat(stockEntity.getId()).isNotNull();
        Assertions.assertThat(stockEntity.getUuid()).isEqualTo("PROD-SADSAD21434");
        Assertions.assertThat(stockEntity.getName()).isEqualTo("Coca cola");
    }
}
