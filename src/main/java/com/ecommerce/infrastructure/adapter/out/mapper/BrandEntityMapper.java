package com.ecommerce.infrastructure.adapter.out.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ecommerce.domain.model.Brand;
import com.ecommerce.infrastructure.adapter.out.out.entities.BrandEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BrandEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    Brand brandEntityToBrand(BrandEntity brandEntity);

    @InheritInverseConfiguration
    BrandEntity brandToBrandEntity(Brand brand);
}
