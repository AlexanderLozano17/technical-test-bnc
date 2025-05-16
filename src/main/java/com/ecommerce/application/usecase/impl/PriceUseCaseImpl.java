package com.ecommerce.application.usecase.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ecommerce.application.dto.PriceDto;
import com.ecommerce.application.mappers.PriceDtoMapper;
import com.ecommerce.application.usecase.PriceUseCase;
import com.ecommerce.domain.model.Price;
import com.ecommerce.domain.service.PriceRepository;
import com.ecommerce.utils.LogHelper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PriceUseCaseImpl implements PriceUseCase {
	
	private final Logger LOGGER = LoggerFactory.getLogger(PriceUseCaseImpl.class);

    public static final String NOT_FOUND_ERROR_MESSAGE = "There is no record";

    private final PriceRepository priceRepository;
    private final PriceDtoMapper priceDtoMapper;

    @Override
    public Optional<PriceDto> getPriceByDate(LocalDateTime date, Long productId, Long brandId) {
    	LOGGER.info(LogHelper.start(getClass(), "getPriceByDate"));
    	
    	Optional<PriceDto> priceDto = priceRepository.getPriceByDate(date, productId, brandId)
                .map(priceDtoMapper::priceToPriceDto);
    	
    	if (priceDto.isPresent()) {
    		LOGGER.info(LogHelper.success(getClass(), "getPriceByDate", ""));
    		LOGGER.info(LogHelper.end(getClass(), "getPriceByDate"));
    		return priceDto;
    	}
    	
    	LOGGER.warn(LogHelper.warn(getClass(), "getPriceByDate", ""));
		LOGGER.info(LogHelper.end(getClass(), "getPriceByDate"));
		return Optional.empty();
    }

	@Override
	public List<PriceDto> getAllPrice() {
		LOGGER.info(LogHelper.start(getClass(), "getAllPrice"));
		List<PriceDto> listPriceDto = priceRepository.getAllPrice().stream()
                .map(price -> mapperPriceToPriceDto(price))
                .collect(Collectors.toList());
		
		return listPriceDto;
	}
	
	private PriceDto mapperPriceToPriceDto(Price price) {		
		return priceDtoMapper.priceToPriceDto(price);
	}

}
