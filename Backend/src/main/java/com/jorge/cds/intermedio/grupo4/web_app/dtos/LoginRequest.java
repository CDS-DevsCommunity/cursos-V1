package com.jorge.cds.intermedio.grupo4.web_app.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    private String username;
    private String password;
}
