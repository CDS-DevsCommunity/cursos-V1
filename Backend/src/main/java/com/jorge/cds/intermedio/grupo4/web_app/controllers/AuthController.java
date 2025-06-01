package com.jorge.cds.intermedio.grupo4.web_app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.jorge.cds.intermedio.grupo4.web_app.dtos.CreateUserDto;
import com.jorge.cds.intermedio.grupo4.web_app.dtos.LoginRequestDTO;
import com.jorge.cds.intermedio.grupo4.web_app.services.AuthService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import com.jorge.cds.intermedio.grupo4.web_app.security.ApiConfig;
import com.jorge.cds.intermedio.grupo4.web_app.security.AuthConstants;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/auth")
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        authService.createUser(createUserDto);
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
        final String token = authService.login(loginRequestDTO);
        final Cookie cookie = createAuthCookie(token);
        response.addCookie(cookie);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        final Cookie cookie = new Cookie(AuthConstants.TOKEN_COOKIE_NAME, StringUtils.EMPTY);
        cookie.setMaxAge(0);
    }

    private Cookie createAuthCookie(String token) {
        final String SAME_SITE_KEY = "SameSite";
        final Cookie cookie = new Cookie(AuthConstants.TOKEN_COOKIE_NAME, token);
        cookie.setHttpOnly(AuthConstants.HTTP_ONLY);
        cookie.setSecure(AuthConstants.COOKIE_SECURE);
        cookie.setMaxAge(AuthConstants.COOKIE_MAX_AGE);
        cookie.setAttribute(SAME_SITE_KEY, AuthConstants.SAME_SITE);
        return cookie;
    }

}