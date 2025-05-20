package com.ecommerce.application.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.application.dto.PriceDto;
import com.ecommerce.application.mapper.PriceDtoMapper;
import com.ecommerce.domain.model.Price;

@ExtendWith(MockitoExtension.class)
class PriceDtoMapperTest {

    private PriceDtoMapper priceDtoMapper;

    @BeforeEach
    void setUp() {
        this.priceDtoMapper = Mappers.getMapper(PriceDtoMapper.class);
    }

    @Test
    void priceDtoToPriceTest() {

    	 PriceDto priceDto = new PriceDto();
    	 priceDto.setPrice(BigDecimal.valueOf(25.45));
    	
        Price priceRes = priceDtoMapper.priceDtoToPrice(priceDto);

        assertEquals(priceDto.getPrice(), priceRes.getPriceAmount());
    }
}
