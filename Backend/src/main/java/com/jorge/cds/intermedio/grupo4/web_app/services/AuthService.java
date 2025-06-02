package com.jorge.cds.intermedio.grupo4.web_app.services;

import com.jorge.cds.intermedio.grupo4.web_app.dtos.CreateUserDto;
import com.jorge.cds.intermedio.grupo4.web_app.dtos.LoginRequestDTO;
import com.jorge.cds.intermedio.grupo4.web_app.entities.User;

public interface AuthService {
    
    String login(LoginRequestDTO loginRequestDTO);

    boolean validateToken(String token);

    String getUserFromToken(String token);

    void createUser(CreateUserDto createUserDto);

    User getUser(Long id);
}
