package com.ecommerce.utils;

/**
 * La clase MessagesResponse es una clase de constantes que define los mensajes
 * estándar de respuesta para una API o un metodo. Está diseñada para centralizar los
 * mensajes utilizados en las respuestas HTTP
 */
public final class MessagesResponse {
	
    private MessagesResponse() {}
    
    /** ====================================================
		    			API MESSAGE SUCCESS
		==================================================== */
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    
    /** ====================================================
                      API MESSAGE SUCCESS
    	==================================================== */
    public static final String LIST_SUCCESS = "Listado obtenido correctamente";
    public static final String RECORD_FOUND = "Registro encontrado";
    public static final String RECORD_NOT_FOUND = "Registro no encontrado";
    public static final String SAVE_SUCCESS = "Registro guardado correctamente";
    public static final String NO_CONTENT = "La solicitud se procesó correctamente, pero no hay contenido disponible.";
    
    /** ====================================================
                      API MESSAGE ERROR
    	==================================================== */
    public static final String UNAUTHORIZED = "No autorizado para realizar esta acción.";
    public static final String FORBIDDEN = "Acceso denegado.";
    public static final String BAD_REQUEST = "Solicitud incorrecta. Verifique los datos enviados.";
    public static final String INTERNAL_SERVER_ERROR = "Error interno del servidor. Intente nuevamente más tarde.";

}

