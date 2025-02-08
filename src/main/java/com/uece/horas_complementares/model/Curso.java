package com.uece.horas_complementares.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uece.horas_complementares.model.user.Aluno;
import com.uece.horas_complementares.model.user.Professor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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
    @JsonIgnore
    private List<Aluno> alunos;

    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<Professor> professores;

    @OneToMany(mappedBy = "idCurso") // Relacionamento com a tabela Evento
    @JsonIgnore
    private List<Evento> eventos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}