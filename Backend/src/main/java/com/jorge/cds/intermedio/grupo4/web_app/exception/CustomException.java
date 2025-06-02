package com.jorge.cds.intermedio.grupo4.web_app.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

/*
 * Clase que pesonaliza las excepciones de la aplicación
 */
@Data
public class CustomException extends RuntimeException {

    private String code;
    private HttpStatus status;

    public CustomException(String code, HttpStatus status, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }

}
