package com.ecommerce.infraestructure.adapters.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.domain.model.Product;
import com.ecommerce.infrastructure.adapter.entities.ProductEntity;
import com.ecommerce.infrastructure.adapter.mapper.ProductEntityMapper;

@ExtendWith(MockitoExtension.class)
class ProductEntityMapperTest {

    private ProductEntityMapper productEntityMapper;

    @BeforeEach
    void setUp() {
        this.productEntityMapper = Mappers.getMapper(ProductEntityMapper.class);
    }

    @Test
    void productEntityToProductTest() {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);

        Product product = productEntityMapper.productEntityToProduct(productEntity);

        assertEquals(productEntity.getId(), product.getId());
    }
}
