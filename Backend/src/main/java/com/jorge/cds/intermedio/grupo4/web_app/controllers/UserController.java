package com.jorge.cds.intermedio.grupo4.web_app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.jorge.cds.intermedio.grupo4.web_app.dtos.showUserDto;
import com.jorge.cds.intermedio.grupo4.web_app.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")        //lista todos los usuarios datos completos
    public List<showUserDto> list(){
        return userService.findAll();
    }
    
}
