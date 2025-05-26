package com.ecommerce.config;

/**
 * Clase que centraliza las rutas base y versiones para la API REST.
 * Esto ayuda a mantener consistencia y facilidad de mantenimiento.
 */
public class ApiVersionPaths {

    /***********************************************
              Versiones generales de la API
     ***********************************************/
    public static final String API_V1 = "/api/v1";

    
    /***********************************************
	    Endpoints generales, relativos a la versión
	 ***********************************************/
    public static final String PRICES = "/prices";

    
    /***********************************************
	  Construcción de rutas completas (version + endpoint)
	 ***********************************************/
    public static final String V1_PRICES = API_V1 + PRICES;
    
    private ApiVersionPaths() {}

}
