package com.uece.horas_complementares.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import java.util.List;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCurso;

    @OneToMany(mappedBy = "curso")
    private List<Aluno> alunos;

    @OneToMany(mappedBy = "curso")
    private List<Professor> professores;

    @OneToMany(mappedBy = "idCurso") // Relacionamento com a tabela Evento
    private List<Evento> eventos;
}