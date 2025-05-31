package com.jorge.cds.intermedio.grupo4.web_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.cds.intermedio.grupo4.web_app.dtos.LoginRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

//@RestController
//@RequestMapping("/api/auth")
public class AuthController {
    // @Autowired
    // private AuthenticationManager authenticationManager; // Delega la autenticacion en un AuthenticationManager

    // @Autowired
    // //private JwtTokenProvider jwtTokenProvider; // Crea y valida JWT (en el caso de que las credenciales sean correctas)

    // @Autowired
    // private HttpServletRequest httpServiceRequest;

    // //Endpoint que recibe un LoginRequest y devuelve un JWT
    // @PostMapping("/login")
    // public ResponseEntity<?> login(@Valid @RequestBody LoginRequest login) {
    //     try {

    //         Authentication authentication = authenticationManager.authenticate( // 1) Autenticar credenciales
    //                 new UsernamePasswordAuthenticationToken(
    //                         login.getUsername(),
    //                         login.getPassword()));
    //         String jwt = jwtTokenProvider.generateToken(authentication); // 2) Generar JWT(por implementar por buenas practicas)

    //         return ResponseEntity.ok(new JwtResponse(jwt)); // 3) Enviar respuesta con el token

    //     } catch (BadCredentialsException ex) {

    //         // 401 si usuario/contraseña no coinciden
    //         return ResponseEntity.status(401).body("Credenciales incorrectas");
    //     } catch (AuthenticationException ex) {

    //         // 500 en caso de otro fallo en autent.
    //         return ResponseEntity.status(500).body("Error en proceso de autenticación");
    //     }
    // }
}
