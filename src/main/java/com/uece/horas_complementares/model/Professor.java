package com.uece.horas_complementares.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PROFESSOR")
public class Professor extends User {

    private boolean coordenador;

    @ManyToOne
    @JoinColumn(name = "idCurso")
    private Curso curso;
}



