package com.jorge.cds.intermedio.grupo4.web_app.security;

/*
 * configuración de la API:
 * esta clase define la configuración de la API, incluyendo el path base y la versión.
 */
public class ApiConfig {

    private static final String COMMON_PATH = "/jorge-cds-api";
    private static final String API_VERSION = "/v1";
    public static final String API_BASE_PATH = COMMON_PATH + API_VERSION;

    private ApiConfig() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

}
