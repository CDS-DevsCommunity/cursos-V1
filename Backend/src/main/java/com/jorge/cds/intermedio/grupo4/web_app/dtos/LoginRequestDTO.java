package com.jorge.cds.intermedio.grupo4.web_app.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
    
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String userName;

    @NotBlank(message = "La contraseña es obligatoria")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
    
}
