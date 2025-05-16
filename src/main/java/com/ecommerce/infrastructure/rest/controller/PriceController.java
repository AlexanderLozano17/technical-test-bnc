package com.ecommerce.infrastructure.rest.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.application.dto.PriceDto;
import com.ecommerce.application.usecase.PriceUseCase;
import com.ecommerce.infrastructure.rest.response.ResponseApi;
import com.ecommerce.infrastructure.rest.response.ResponseError;
import com.ecommerce.utils.ApiMessages;
import com.ecommerce.utils.LogHelper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(value = "/api/price", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Price", description = "Price consultation API")
public class PriceController {

	private final Logger LOGGER = LoggerFactory.getLogger(PriceController.class);
	
    private final PriceUseCase priceUseCase;

    @Operation(summary = "Get price for a product", description = "Get the applicable price for a product at a specific date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PriceDto.class))}),
            @ApiResponse(responseCode = "404", description = "Price not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))})
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseApi<PriceDto>> getPriceByDate(
            @Parameter(description = "Request with the application date information", required = true)
            @RequestParam("applicationDate")
            LocalDateTime applicationDate,
            @Parameter(description = "Request with the product id information", required = true)
            @RequestParam("productId")
            @Min(value = 1, message = "productId must be greater than 0")
            Long productId,
            @Parameter(description = "Request with the brand id information", required = true)
            @RequestParam("brandId")
            @Min(value = 1, message = "brandId must be greater than 0")
            Long brandId) {
    	
    	LOGGER.info(LogHelper.start(getClass(), "getPriceByDate"));
    	
    	Optional<PriceDto> priceDto = priceUseCase.getPriceByDate(applicationDate, productId, brandId);
    	
    	if (priceDto.isPresent()) {
    		LOGGER.info(LogHelper.success(getClass(), "getPriceByDate", ApiMessages.RECORD_FOUND));
    		LOGGER.info(LogHelper.end(getClass(), "getPriceByDate"));
			return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, priceDto.get()));

    	} else {
    		LOGGER.warn(LogHelper.warn(getClass(), "getPriceByDate", ApiMessages.RECORD_NOT_FOUND));
    		LOGGER.info(LogHelper.end(getClass(), "getPriceByDate"));
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
    	}
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseApi<List<PriceDto>>> getAllPrice() {
    	
    	LOGGER.info(LogHelper.start(getClass(), "getAllPrice"));
    	
    	List<PriceDto> listPriceDto = priceUseCase.getAllPrice();
    	
    	if (listPriceDto.size()) {
    		LOGGER.info(LogHelper.success(getClass(), "getAllPrice", ApiMessages.RECORD_FOUND));
    		LOGGER.info(LogHelper.end(getClass(), "getAllPrice"));
			return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, listPriceDto));

    	} else {
    		LOGGER.warn(LogHelper.warn(getClass(), "getAllPrice", ApiMessages.RECORD_NOT_FOUND));
    		LOGGER.info(LogHelper.end(getClass(), "getAllPrice"));
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
    	}
    }
}
