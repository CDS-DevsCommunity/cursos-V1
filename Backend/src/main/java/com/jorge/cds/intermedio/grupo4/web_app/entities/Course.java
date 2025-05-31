package com.jorge.cds.intermedio.grupo4.web_app.entities;

import java.time.Duration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "curso")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Course {

    @Id
    @Column(name = "id_curso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    @NotNull
    @NotBlank
    private String title;

    @Column(name = "descripcion", nullable = false)
    @NotNull
    @NotBlank
    private String description;

    @Column(name = "duracion", nullable = false)
    @NotNull
    @NotBlank
    private Duration duration;  //duda a a la hora de guardar el tiempo del video

}
