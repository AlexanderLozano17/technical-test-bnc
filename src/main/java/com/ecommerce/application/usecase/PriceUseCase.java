package com.ecommerce.application.usecase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.ecommerce.application.dto.PriceDto;

public interface PriceUseCase {

	/**
	 * 
	 * @param date
	 * @param productId
	 * @param brandId
	 * @return
	 */
    Optional<PriceDto> getPriceByDate(LocalDateTime date, Long productId, Long brandId);

    /**
     * 
     * @return
     */
	List<PriceDto> getAllPrice();
}
