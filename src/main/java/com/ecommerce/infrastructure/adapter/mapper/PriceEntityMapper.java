package com.ecommerce.infrastructure.adapter.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ecommerce.domain.model.Price;
import com.ecommerce.infrastructure.adapter.entities.PriceEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductEntityMapper.class,
        BrandEntityMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PriceEntityMapper {

    @Mapping(target = "priceList", source = "priceList")
    @Mapping(target = "brand", source = "brandEntity")
    @Mapping(target = "product", source = "productEntity")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "priceAmount", source = "price")
    @Mapping(target = "currency", source = "currency")
    Price priceEntitytoPrice(PriceEntity priceEntity);

    @InheritInverseConfiguration
    PriceEntity priceToPriceEntity(Price price);
}
