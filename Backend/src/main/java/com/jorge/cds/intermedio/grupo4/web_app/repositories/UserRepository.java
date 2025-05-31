package com.jorge.cds.intermedio.grupo4.web_app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jorge.cds.intermedio.grupo4.web_app.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    //cuando se requiere de consultas personalizadas, se pueden agregar aquí aunque no es necesario
    //solo si es algo complejo en los casos de consultas entre 2 entidades
    
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);

}
