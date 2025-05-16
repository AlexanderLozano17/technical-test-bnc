package com.ecommerce.infrastructure.adapter.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ecommerce.domain.model.Product;
import com.ecommerce.infrastructure.adapter.entities.ProductEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ProductEntity productToProductEntity(Product product);

    @InheritInverseConfiguration
    Product productEntityToProduct(ProductEntity productEntity);
}
