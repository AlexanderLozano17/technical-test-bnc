package com.ecommerce.application.usecase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.ecommerce.application.dto.PriceDto;

public interface PriceUseCase {

    Optional<PriceDto> getPriceByDate(LocalDateTime date, Long productId, Long brandId);

	List<PriceDto> getAllPrice();
}
