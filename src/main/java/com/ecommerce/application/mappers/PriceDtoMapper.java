package com.ecommerce.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ecommerce.application.dto.PriceDto;
import com.ecommerce.domain.model.Price;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceDtoMapper {

    @Mapping(target = "priceList", source = "priceList")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "price", source = "priceAmount")
    PriceDto priceToPriceDto(Price price);

    @Mapping(target = "priceList", source = "priceList")
    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "brand.id", source = "brandId")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "priceAmount", source = "price")
    @Mapping(target = "currency", ignore = true)
    @Mapping(target = "priority", ignore = true)
    Price priceDtoToPrice(PriceDto price);

}
