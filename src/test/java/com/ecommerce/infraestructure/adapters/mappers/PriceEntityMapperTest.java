package com.ecommerce.infraestructure.adapters.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ecommerce.domain.model.Brand;
import com.ecommerce.domain.model.Price;
import com.ecommerce.domain.model.Product;
import com.ecommerce.infrastructure.adapter.entities.PriceEntity;
import com.ecommerce.infrastructure.adapter.mapper.PriceEntityMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PriceEntityMapperTest {

    @Autowired
    private PriceEntityMapper priceEntityMapper;

    @Test
    void priceToPriceEntityTest() {

        Price price = new Price();
        price.setPriceAmount(BigDecimal.valueOf(35.50));
        price.setProduct(new  Product());
        price.setBrand(new Brand());
        
        PriceEntity priceEntity = priceEntityMapper.priceToPriceEntity(price);

        assertEquals(price.getPriceAmount(), priceEntity.getPrice());
    }
}
