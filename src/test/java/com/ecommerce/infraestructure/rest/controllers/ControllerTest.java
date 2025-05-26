package com.ecommerce.infraestructure.rest.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.config.ApiVersionPaths;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource({
            "2020-06-14T10:00:00, 35455, 1, 35.50, 2020-06-14T00:00:00, 2020-12-31T23:59:59",
            "2020-06-14T16:00:00, 35455, 1, 25.45, 2020-06-14T15:00:00, 2020-06-14T18:30:00",
            "2020-06-14T21:00:00, 35455, 1, 35.50, 2020-06-14T00:00:00, 2020-12-31T23:59:59",
            "2020-06-15T10:00:00, 35455, 1, 30.50, 2020-06-15T00:00:00, 2020-06-15T11:00:00",
            "2020-06-15T21:00:00, 35455, 1, 38.95, 2020-06-15T16:00:00, 2020-12-31T23:59:59"
    })
    void testPriceRequest(String applicationDate, 
    		int productId, 
    		int brandId, 
    		double expectedPrice,
    		String startDate, 
    		String endDate) throws Exception {
    	
        mockMvc.perform(get(ApiVersionPaths.V1_PRICES)
        		 	.param("applicationDate", applicationDate)
        		 	.param("productId", String.valueOf(productId))
        		 	.param("brandId", String.valueOf(brandId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(expectedPrice))
                .andExpect(jsonPath("$.startDate").value(startDate))
                .andExpect(jsonPath("$.endDate").value(endDate));
    }

    @Test
    void testNotFoundPrice() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        int productId = 35455;
        int brandId = 2;

        mockMvc.perform(get(ApiVersionPaths.V1_PRICES)
	    		 	.param("applicationDate", date.toString())
	    		 	.param("productId", String.valueOf(productId))
	    		 	.param("brandId", String.valueOf(brandId)))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("getInvalidTestData")
    void testInvalidParameters(String applicationDate, 
    		String productId, 
    		String brandId, 
    		int httpStatus, 
    		String message) throws Exception {
    	
    	mockMvc.perform(get("/api/price/{applicationDate}/{productId}/{brandId}", applicationDate, productId, brandId))
        		.andExpect(status().is(httpStatus));
    }
    
    static Stream<Arguments> getInvalidTestData() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        int httpStatusValue = HttpStatus.NOT_FOUND.value();
        return Stream.<Arguments>builder()
                .add(Arguments.of(null, "35455", "1", httpStatusValue, ""))
                .add(Arguments.of(date, null, "1", httpStatusValue, ""))
                .add(Arguments.of(date, "35455", null, httpStatusValue, ""))                
                .build();
    }
}
