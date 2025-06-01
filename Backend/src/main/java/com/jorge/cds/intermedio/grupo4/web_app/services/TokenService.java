package com.jorge.cds.intermedio.grupo4.web_app.services;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateToken(Authentication authentication);

    String getUserFromToken(String token);

    boolean validateToken(String token);

}
