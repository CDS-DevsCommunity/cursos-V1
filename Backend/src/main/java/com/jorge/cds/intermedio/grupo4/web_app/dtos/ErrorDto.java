package com.jorge.cds.intermedio.grupo4.web_app.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {

    private String code;
    private String message;

}
