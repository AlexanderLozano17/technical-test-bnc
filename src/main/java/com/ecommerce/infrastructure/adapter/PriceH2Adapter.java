package com.ecommerce.infrastructure.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.application.dto.PriceDto;
import com.ecommerce.domain.model.Price;
import com.ecommerce.domain.service.PriceRepository;
import com.ecommerce.infrastructure.adapter.entities.BrandEntity;
import com.ecommerce.infrastructure.adapter.entities.ProductEntity;
import com.ecommerce.infrastructure.adapter.mapper.PriceEntityMapper;
import com.ecommerce.infrastructure.adapter.repository.PriceJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PriceH2Adapter implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;
    private final PriceEntityMapper priceDboMapper;

    @Override
    public Optional<Price> getPriceByDate(LocalDateTime date, Long productId, Long brandId) {

        ProductEntity product = ProductEntity.builder().id(productId).build();
        BrandEntity brand = BrandEntity.builder().id(brandId).build();

        return priceJpaRepository.findFirstPriceByDateAndProductAndBrand(date, product, brand)
                .map(priceDboMapper::priceEntitytoPrice);
    }

	@Override
	public List<PriceDto> getAllPrice() {
		// TODO Auto-generated method stub
		return null;
	}

}
