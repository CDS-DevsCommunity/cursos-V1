package com.jorge.cds.intermedio.grupo4.web_app.services;

import java.util.List;
import java.util.Optional;

import com.jorge.cds.intermedio.grupo4.web_app.dtos.UserDto;
import com.jorge.cds.intermedio.grupo4.web_app.entities.User;

public interface UserService {
    
    List<UserDto> findAll();

    Optional<User> finById(Long id);

    User save(User user);

    Optional<User> update(Long id);

    Optional<User> delete(Long id);

    String userRole(User user);

}