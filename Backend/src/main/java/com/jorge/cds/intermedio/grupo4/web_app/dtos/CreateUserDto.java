package com.jorge.cds.intermedio.grupo4.web_app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    //@JsonIgnore
    @NotNull(message = "El nombre completo no puede ser vacio")
    @NotBlank(message = "El nombre completo no puede estar vacío")
    private String fullName;

    @NotNull(message = "El nombre de Usuario no puede ser vacio")
    @NotBlank(message = "El nombre de Usuario no puede estar vacío")
    private String userName;

    @NotNull(message = "El correo no puede ser vacio")
    @NotBlank(message = "El correo no puede estar vacío")
    private String email;

    //@JsonIgnore
    @NotNull(message = "La contraseña no puede ser vacio")
    @NotBlank(message = "La contraseña no puede estar vacío")
    private String password;

}
