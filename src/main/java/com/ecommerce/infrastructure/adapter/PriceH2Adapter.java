package com.ecommerce.infrastructure.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ecommerce.domain.model.Price;
import com.ecommerce.domain.service.PriceRepository;
import com.ecommerce.infrastructure.adapter.entities.BrandEntity;
import com.ecommerce.infrastructure.adapter.entities.PriceEntity;
import com.ecommerce.infrastructure.adapter.entities.ProductEntity;
import com.ecommerce.infrastructure.adapter.mapper.PriceEntityMapper;
import com.ecommerce.infrastructure.adapter.repository.PriceJpaRepository;
import com.ecommerce.utils.LogHelper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PriceH2Adapter implements PriceRepository {
	
	private final Logger LOGGER = LoggerFactory.getLogger(PriceH2Adapter.class);

    private final PriceJpaRepository priceJpaRepository;
    private final PriceEntityMapper priceDboMapper;

    @Override
    public Optional<Price> getPriceByDate(LocalDateTime date, Long productId, Long brandId) {
    	LOGGER.info(LogHelper.start(getClass(), "getPriceByDate"));

        ProductEntity product = new ProductEntity();
        product.setId(productId);
        
        BrandEntity brand = new BrandEntity();
        brand.setId(brandId);

		LOGGER.info(LogHelper.end(getClass(), "getPriceByDate"));
        return priceJpaRepository.findFirstPriceByDateAndProductAndBrand(date, product, brand)
                .map(priceDboMapper::priceEntitytoPrice);
    }

	@Override
	public List<Price> getAllPrice() {
		LOGGER.info(LogHelper.start(getClass(), "getAllPrice"));
		
		List<Price> listPrice = priceJpaRepository.findAll().stream()
				.map(priceDboMapper::priceEntitytoPrice)
				.collect(Collectors.toList());
		
		LOGGER.info(LogHelper.end(getClass(), "getAllPrice"));
		return listPrice;
	}

}
