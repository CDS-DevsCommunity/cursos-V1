package com.jorge.cds.intermedio.grupo4.web_app.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jorge.cds.intermedio.grupo4.web_app.dtos.AuthMapper;
import com.jorge.cds.intermedio.grupo4.web_app.dtos.CreateUserDto;
import com.jorge.cds.intermedio.grupo4.web_app.dtos.LoginRequestDTO;
import com.jorge.cds.intermedio.grupo4.web_app.entities.User;
import com.jorge.cds.intermedio.grupo4.web_app.exception.CustomException;
import com.jorge.cds.intermedio.grupo4.web_app.repositories.UserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {
    
    private final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationConfiguration authenticationConfiguration;

    //forma de hacer inyeccion de dependencias
    public AuthServiceImpl(UserRepository userRepository, TokenService tokenService, PasswordEncoder passwordEncoder, AuthenticationConfiguration authenticationConfiguration) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Override
    public void createUser(final CreateUserDto createUserDto) {
        final User createUser = AuthMapper.fromDto(createUserDto);
        createUser.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        userRepository.save(createUser);
    }

    @Override
    public User getUser(final Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new CustomException("P-409", HttpStatus.CONFLICT, "USER_NOT_FOUND"));
    }

    @Override
    public String login(final LoginRequestDTO loginRequest) {
        try {   
            final AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
            final Authentication authRequest = AuthMapper.fromDto(loginRequest);
            final Authentication authentication = authenticationManager.authenticate(authRequest);
            return tokenService.generateToken(authentication);

        } catch (Exception e) {
            logger.error("[USER] : Error while trying to login", e);
            throw new RuntimeException("Error while trying to login");
        }
    }

    @Override
    public boolean validateToken(final String token) {
        return tokenService.validateToken(token);
    }

    @Override
    public String getUserFromToken(final String token) {
        return tokenService.getUserFromToken(token);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        logger.info("[USER] : Loading user by email {}", username);
        return userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
