package com.jorge.cds.intermedio.grupo4.web_app.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "estudiante")
@PrimaryKeyJoinColumn(name = "id_usuario")
@SuperBuilder
@NoArgsConstructor
public class Student extends User {

    /*
     * getAuthorities() es un método de la interfaz UserDetails que devuelve en este caso un rol o el rol del usuario definido
     * por la herencia de User.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
    }
    
}
