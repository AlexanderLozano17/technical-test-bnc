package com.ecommerce.application.port.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ecommerce.application.dto.PriceDto;
import com.ecommerce.application.mapper.PriceDtoMapper;
import com.ecommerce.application.port.in.PriceUseCase;
import com.ecommerce.domain.service.PriceRepository;
import com.ecommerce.utils.LogHelper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PriceUseCaseService implements PriceUseCase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PriceUseCaseService.class);

    private final PriceRepository priceRepository;
    private final PriceDtoMapper priceDtoMapper;

    @Override
    public Optional<PriceDto> getPriceByDate(LocalDateTime date, Long productId, Long brandId) {
    	LOGGER.info(LogHelper.start(PriceUseCaseService.class, "getPriceByDate"));
    	
    	Optional<PriceDto> priceDto = priceRepository.getPriceByDate(date, productId, brandId)
                .map(priceDtoMapper::priceToPriceDto);

		LOGGER.info(LogHelper.end(PriceUseCaseService.class, "getPriceByDate"));
		return priceDto;
    }
}
