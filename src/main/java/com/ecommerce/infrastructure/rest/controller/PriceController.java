package com.ecommerce.infrastructure.rest.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.application.dto.PriceDto;
import com.ecommerce.application.usecase.PriceUseCase;
import com.ecommerce.infrastructure.rest.response.ResponseApi;
import com.ecommerce.infrastructure.rest.response.ResponseError;
import com.ecommerce.utils.LogHelper;
import com.ecommerce.utils.MessagesResponse;

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
@Tag(name = "Precios", description = "Price consultation API")
public class PriceController {

	private final Logger LOGGER = LoggerFactory.getLogger(PriceController.class);
	
    private final PriceUseCase priceUseCase;
    
    @Operation(
	    summary = "Obtener todos los precios de productos",
	    description = "Recupera la lista completa de precios disponibles para todos los productos.",
	    tags = {"Precios"}
	)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de precios encontrada",
	        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PriceDto.class))),
	    @ApiResponse(responseCode = "404", description = "No se encontraron precios",
	        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))    	        ),
	    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta",
	        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)))
	})
    @GetMapping()
    public ResponseEntity<List<PriceDto>> getAllPrice() {    	
    	LOGGER.info(LogHelper.start(getClass(), "getAllPrice"));
    	
    	List<PriceDto> listPriceDto = priceUseCase.getAllPrice();
	
		LOGGER.info(LogHelper.success(getClass(), "getAllPrice", MessagesResponse.RECORD_FOUND));
		LOGGER.info(LogHelper.end(getClass(), "getAllPrice"));
		return ResponseEntity.ok(listPriceDto);
    }

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
    	
    @GetMapping("/{applicationDate}/{productId}/{brandId}")
    public ResponseEntity<PriceDto> getPriceByDate(
            @Parameter(description = "Request with the application date information", required = true)
            @PathVariable() LocalDateTime applicationDate,
            
            @Parameter(description = "Request with the product id information", required = true)
            @PathVariable
            @Min(value = 1, message = "productId must be greater than 0")
            Long productId,
            
            @Parameter(description = "Request with the brand id information", required = true)
            @PathVariable
            @Min(value = 1, message = "brandId must be greater than 0")
            Long brandId) {
    	
    	LOGGER.info(LogHelper.start(getClass(), "getPriceByDate"));
    	
    	PriceDto priceDto = this.priceUseCase.getPriceByDate(applicationDate, productId, brandId).get();    	
    	return ResponseEntity.ok(priceDto);
    	
    }

}
