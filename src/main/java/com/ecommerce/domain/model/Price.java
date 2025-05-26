package com.ecommerce.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Price {

    private Long priceList;
    private Brand brand;
    private Product product;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private BigDecimal priceAmount;
    private String currency;

}
