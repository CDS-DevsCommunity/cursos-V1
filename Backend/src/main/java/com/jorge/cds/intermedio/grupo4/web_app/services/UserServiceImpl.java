package com.jorge.cds.intermedio.grupo4.web_app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jorge.cds.intermedio.grupo4.web_app.dtos.UserDto;
import com.jorge.cds.intermedio.grupo4.web_app.entities.Instructor;
import com.jorge.cds.intermedio.grupo4.web_app.entities.Student;
import com.jorge.cds.intermedio.grupo4.web_app.entities.User;
import com.jorge.cds.intermedio.grupo4.web_app.exception.CustomException;
import com.jorge.cds.intermedio.grupo4.web_app.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> delete(Long id) {// falta
        return null;
    }

    @Override // lista todos los usuarios datos del dto
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<UserDto> dtos = new ArrayList<>();
        userRepository.findAll().forEach(user -> dtos.add(new UserDto(user)));
        return dtos;
    }

    @Override
    public Optional<User> finById(Long id) {// falta
        return Optional.empty();
    }

    @Override
    @Transactional
    public User save(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new CustomException("P-409",
                    HttpStatus.CONFLICT,
                    "El email ya existe");
        }

        if (userRepository.existsByUserName(user.getUserName())) {
            throw new CustomException("P-409",
                    HttpStatus.CONFLICT,
                    "El userName ya existe");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public String userRole(User user) {
        if (user instanceof Student) {
            return "STUDENT";
        }
        if (user instanceof Instructor) {
            return "INSTRUCTOR";
        }
        return "INSTRUCTOR";
    }

    @Override
    @Transactional
    public Optional<User> update(Long id) {// falta
        return Optional.empty();
    }

}