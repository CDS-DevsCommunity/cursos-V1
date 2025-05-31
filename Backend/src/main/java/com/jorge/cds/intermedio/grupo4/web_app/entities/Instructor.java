package com.jorge.cds.intermedio.grupo4.web_app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "instructor")
@PrimaryKeyJoinColumn(name = "id_usuario")
@SuperBuilder
@NoArgsConstructor
public class Instructor extends User {

}
