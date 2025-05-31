package com.jorge.cds.intermedio.grupo4.web_app.entities;

import com.jorge.cds.intermedio.grupo4.web_app.dtos.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "nombre_usuario", nullable = false, unique = true)
    @NotNull
    @NotBlank
    private String userName;

    @Column(name = "nombre_completo", nullable = false)
    @NotNull
    @NotBlank
    private String fullName;

    @Column(name = "correo", nullable = false, unique = true)
    @NotNull
    @NotBlank
    private String email;

    @Column(name = "contraseña", nullable = false)
    @NotNull
    @NotBlank
    private String password;

    public User(UserDto dto) {
        this.userName = dto.getUserName();
        this.fullName = dto.getFullName();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
    }

}
