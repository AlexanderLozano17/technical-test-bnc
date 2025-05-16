package com.ecommerce.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.ecommerce.application.dto.PriceDto;
import com.ecommerce.domain.model.Price;

public interface PriceRepository {

    Optional<Price> getPriceByDate(LocalDateTime date, Long productId, Long brandId);
    
    List<PriceDto> getAllPrice();
}
