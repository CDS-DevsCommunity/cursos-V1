package com.jorge.cds.intermedio.grupo4.web_app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jorge.cds.intermedio.grupo4.web_app.entities.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

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

    //lo tachado para nomostrar la contraseña y el nombre completo en el GET list
    public UserDto(User user) {
        this.fullName = user.getFullName();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

}
