package com.uece.horas_complementares.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity(name = "Professor")
public class Professor {
    
    @Id
    private Long matricula;//Pk
    private String nome;
    private String email;
    private boolean emailValidado;
    private String senha;
    private boolean coordenador;
    private String token;

    @ManyToOne
    @JoinColumn(name = "idCurso")//Fk para tabela Curso
    private Curso idCurso;


}
