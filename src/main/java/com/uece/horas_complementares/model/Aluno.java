package com.uece.horas_complementares.model;

import javax.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import java.util.List;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Aluno {

    @Id
    private Long matricula;
    private String nome;
    private String email;
    private boolean emailValidado;
    private String senha;
    private String token;

    @ManyToOne
    @JoinColumn(name = "idCurso") // Fk para tabela Curso
    private Curso idCurso;

    @OneToMany(mappedBy = "matriculaAluno") // Relacionamento com a tabela Inscricao
    @JoinColumn(name = "matriculaAluno")
    private List<Inscricao> inscricoes;

    @OneToMany(mappedBy = "matriculaAluno") // Relacionamento com a tabela Presença
    @JoinColumn(name = "matriculaAluno")
    private List<Presenca> presencas;

    @OneToOne(mappedBy = "matriculaAluno") // Relacionamento com a tabela HoraComplementar
    @JoinColumn(name = "matriculaAluno")
    private HoraComplementar horaComplementar;
}
