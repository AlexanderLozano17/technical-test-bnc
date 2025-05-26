package com.ecommerce.infrastructure.adapter.out;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.domain.model.Price;
import com.ecommerce.domain.service.PriceRepository;
import com.ecommerce.infrastructure.adapter.out.mapper.PriceEntityMapper;
import com.ecommerce.infrastructure.adapter.out.out.entities.BrandEntity;
import com.ecommerce.infrastructure.adapter.out.out.entities.ProductEntity;
import com.ecommerce.infrastructure.adapter.out.repository.PriceJpaRepository;
import com.ecommerce.utils.LogHelper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PriceH2Adapter implements PriceRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PriceH2Adapter.class);
	
	private static final Pageable FIRST_RESULT_ONLY = PageRequest.of(0, 1);

    private final PriceJpaRepository priceJpaRepository;
    private final PriceEntityMapper priceDboMapper;

    @Override
    public Optional<Price> getPriceByDate(LocalDateTime date, Long productId, Long brandId) {
    	LOGGER.info(LogHelper.start(PriceH2Adapter.class, "getPriceByDate"));

        ProductEntity product = new ProductEntity();
        product.setId(productId);
        
        BrandEntity brand = new BrandEntity();
        brand.setId(brandId);

		LOGGER.info(LogHelper.end(PriceH2Adapter.class, "getPriceByDate"));
        return priceJpaRepository.findTopByProductAndBrandAndApplicationDate(date, product, brand, FIRST_RESULT_ONLY).stream()
        		.findFirst()
                .map(priceDboMapper::priceEntitytoPrice);
    }

}
