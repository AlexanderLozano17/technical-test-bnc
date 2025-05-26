package com.ecommerce.infraestructure.adapters.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ecommerce.infrastructure.adapter.out.out.entities.BrandEntity;
import com.ecommerce.infrastructure.adapter.out.out.entities.PriceEntity;
import com.ecommerce.infrastructure.adapter.out.out.entities.ProductEntity;
import com.ecommerce.infrastructure.adapter.out.repository.PriceJpaRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PriceJpaRepositoryTest {
	
	private static final Pageable FIRST_RESULT_ONLY = PageRequest.of(0, 1);
	
    @Autowired
    private PriceJpaRepository priceJpaRepository;

    @Test
    void test1FindFirstPriceByDateAndProductAndBrand() {

        LocalDateTime applicationDate = LocalDateTime.of(2020, 06, 14, 10, 00);
        
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(35455L);
        productEntity.setName("Product 1");
       
        
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1L);
        brandEntity.setName("Zara");
        
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setPriceList(1L);
        priceEntity.setProductEntity(productEntity);
        priceEntity.setBrandEntity(brandEntity);
        priceEntity.setStartDate(LocalDateTime.of(2020, 06, 14, 00, 00));
        priceEntity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        priceEntity.setPriority(0);
        priceEntity.setPrice(BigDecimal.valueOf(35.50).setScale(2));
        priceEntity.setCurrency("EUR");
 
        Optional<PriceEntity> result = priceJpaRepository.findTopByProductAndBrandAndApplicationDate(
                applicationDate, productEntity, brandEntity, FIRST_RESULT_ONLY).stream().findFirst();

        assertEquals(Optional.of(priceEntity), result);
    }

    @Test
    void test2FindFirstPriceByDateAndProductAndBrand() {

        LocalDateTime applicationDate = LocalDateTime.of(2020, 06, 14, 16, 00);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(35455L);
        productEntity.setName("Product 1");        
        
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1L);
        brandEntity.setName("Zara");
        
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setPriceList(2L);
        priceEntity.setProductEntity(productEntity);
        priceEntity.setBrandEntity(brandEntity);
        priceEntity.setStartDate(LocalDateTime.of(2020, 06, 14, 15, 00));
        priceEntity.setEndDate(LocalDateTime.of(2020, 06, 14, 18, 30));
        priceEntity.setPriority(1);
        priceEntity.setPrice(BigDecimal.valueOf(25.45).setScale(2));
        priceEntity.setCurrency("EUR");

        Optional<PriceEntity> result = priceJpaRepository.findTopByProductAndBrandAndApplicationDate(
        		applicationDate, productEntity, brandEntity, FIRST_RESULT_ONLY)
        		.stream().findFirst();

        assertEquals(Optional.of(priceEntity), result);
    }

}
