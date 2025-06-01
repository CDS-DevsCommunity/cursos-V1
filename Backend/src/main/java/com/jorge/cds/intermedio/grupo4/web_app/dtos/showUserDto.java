package com.jorge.cds.intermedio.grupo4.web_app.dtos;

import com.jorge.cds.intermedio.grupo4.web_app.entities.User;

import lombok.Data;

@Data
public class showUserDto {

    private Long Id;

    private String userName;

    private String fullName;

    private String email;

    private String password;

    public showUserDto(User user) {
        this.Id = user.getId();
        this.userName = user.getUserName();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

}
