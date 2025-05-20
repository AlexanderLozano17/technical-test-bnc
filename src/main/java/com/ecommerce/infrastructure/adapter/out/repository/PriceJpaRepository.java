package com.ecommerce.infrastructure.adapter.out.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.infrastructure.adapter.out.out.entities.BrandEntity;
import com.ecommerce.infrastructure.adapter.out.out.entities.PriceEntity;
import com.ecommerce.infrastructure.adapter.out.out.entities.ProductEntity;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p " +
            "WHERE p.productEntity = :product " +
            	"AND p.brandEntity = :brand " +
            	"AND p.startDate < :applicationDate " +
            	"AND p.endDate > :applicationDate " +
            "ORDER BY p.priority DESC " +
            "LIMIT 1 ")
    Optional<PriceEntity> findFirstPriceByDateAndProductAndBrand(
            @Param("applicationDate") LocalDateTime applicationDate,
            @Param("product") ProductEntity product,
            @Param("brand") BrandEntity brand);

}
