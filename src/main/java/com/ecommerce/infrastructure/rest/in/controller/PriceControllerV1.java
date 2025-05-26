package com.ecommerce.infrastructure.rest.in.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.application.dto.PriceDto;
import com.ecommerce.application.port.in.PriceUseCase;
import com.ecommerce.config.ApiVersionPaths;
import com.ecommerce.infrastructure.rest.in.response.ResponseError;
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
@RequestMapping(value = ApiVersionPaths.V1_PRICES, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Precios", description = "Price consultation API")
public class PriceControllerV1 {

	private static final Logger LOGGER = LoggerFactory.getLogger(PriceControllerV1.class);
	
    private final PriceUseCase priceUseCase;
    
    @Operation(
	    summary = "Obtener el precio aplicable para un producto en una fecha específica",
	    description = "Devuelve el precio correspondiente a un producto y marca en una fecha y hora determinadas.",
	    tags = {"Precios"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Precio encontrado", 
        		content = @Content(mediaType = "application/json", schema = @Schema(implementation = PriceDto.class))),
        @ApiResponse( responseCode = "404", description = "Precio no encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida", 
        	content = @Content( mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)))
    })
    	
    @GetMapping
    public  ResponseEntity<EntityModel<PriceDto>> getPriceByDate(
    		@Parameter(description = "Fecha de aplicación", required = true)
            @RequestParam LocalDateTime applicationDate,
            
            @Parameter(description = "ID del producto", required = true)
            @RequestParam @Min(value = 1, message = "El productId debe ser mayor a 0") 
    		Long productId,

            @Parameter(description = "ID de la marca", required = true)
            @RequestParam @Min(value = 1, message = "El brandId debe ser mayor a 0") 
    		Long brandId) {
    	
    	LOGGER.info(LogHelper.start(PriceControllerV1.class, "getPriceByDate"));    	
    	Optional<PriceDto> priceOpt = priceUseCase.getPriceByDate(applicationDate, productId, brandId);

         return priceOpt
                 .map(price -> {
                     EntityModel<PriceDto> resource = EntityModel.of(price);
                     resource.add(Link.of(ApiVersionPaths.V1_PRICES + "?applicationDate=" + applicationDate + "&productId=" + productId + "&brandId=" + brandId).withSelfRel());
                     return ResponseEntity.ok(resource);
                 })
                 .orElseGet(() -> {
                	 String message = "Precio no encontrado para fecha=" + applicationDate + ", producto=" + productId + ", marca=" + brandId;
                     LOGGER.warn(LogHelper.warn(PriceControllerV1.class, "getPriceByDate", message));
                     return ResponseEntity.notFound().build();
                 });
    }

}
