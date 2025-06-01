package com.jorge.cds.intermedio.grupo4.web_app.dtos;

import com.jorge.cds.intermedio.grupo4.web_app.entities.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthMapper {

    public static User fromDto(CreateUserDto dto) {
        return User.builder()
            .userName(dto.getUserName())
            .fullName(dto.getFullName())
            .email(dto.getEmail())
            .password(dto.getPassword())
            .build();
    }

    public static Authentication fromDto(LoginRequestDTO dto) {
        return new UsernamePasswordAuthenticationToken(
            dto.getUserName(),
            dto.getPassword()
        );
    }
    
}
