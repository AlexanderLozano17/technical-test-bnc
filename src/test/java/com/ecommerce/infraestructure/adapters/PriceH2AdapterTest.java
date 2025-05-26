package com.ecommerce.infraestructure.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.ecommerce.domain.model.Price;
import com.ecommerce.infrastructure.adapter.out.PriceH2Adapter;
import com.ecommerce.infrastructure.adapter.out.mapper.PriceEntityMapper;
import com.ecommerce.infrastructure.adapter.out.out.entities.PriceEntity;
import com.ecommerce.infrastructure.adapter.out.repository.PriceJpaRepository;

@ExtendWith(MockitoExtension.class)
class PriceH2AdapterTest {
	
	private static final Pageable FIRST_RESULT_ONLY = PageRequest.of(0, 1);

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @Mock
    private PriceEntityMapper priceDboMapper;

    @InjectMocks
    private PriceH2Adapter priceH2Adapter;

    @Test
    void testGetPriceByDate() {

        LocalDateTime date = LocalDateTime.now();
        Long productId = 35455L;
        Long brandId = 1L;
        
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setPriceList(10L);
        
        Price price = new Price();
        price.setPriceList(10L);

        when(priceJpaRepository.findTopByProductAndBrandAndApplicationDate(any(), any(), any(), any(Pageable.class)))
        	.thenReturn(List.of(priceEntity));
        
        when(priceDboMapper.priceEntitytoPrice(priceEntity)).thenReturn(price);

        Optional<Price> result = priceH2Adapter.getPriceByDate(date, productId, brandId);

        assertEquals(Optional.of(price), result);
    }

}
