package com.jorge.cds.intermedio.grupo4.web_app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.jorge.cds.intermedio.grupo4.web_app.dtos.CreateUserDto;
import com.jorge.cds.intermedio.grupo4.web_app.dtos.showUserDto;
import com.jorge.cds.intermedio.grupo4.web_app.entities.User;
import com.jorge.cds.intermedio.grupo4.web_app.services.UserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")        //lista todos los usuarios datos completos
    public List<showUserDto> list(){
        return userService.findAll();
    }

    @PostMapping("/register")   //registra un nuevo usuario
    public ResponseEntity<?> create(@Valid @RequestBody CreateUserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(new User(user)));
    }
    
}
