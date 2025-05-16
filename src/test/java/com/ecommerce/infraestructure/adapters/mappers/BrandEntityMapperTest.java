package com.ecommerce.infraestructure.adapters.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.domain.model.Brand;
import com.ecommerce.infrastructure.adapter.entities.BrandEntity;
import com.ecommerce.infrastructure.adapter.mapper.BrandEntityMapper;

@ExtendWith(MockitoExtension.class)
class BrandEntityMapperTest {

    private BrandEntityMapper brandEntityMapper;

    @BeforeEach
    void setUp() {
        this.brandEntityMapper = Mappers.getMapper(BrandEntityMapper.class);
    }

    @Test
    void brandToBrandEntityTest() {

        Brand brand = new Brand();
        brand.setId(1L);

        BrandEntity brandEntity = brandEntityMapper.brandToBrandEntity(brand);

        assertEquals(brand.getId(), brandEntity.getId());
    }
}
