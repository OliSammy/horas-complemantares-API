package com.uece.horas_complementares.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Presenca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "idInscricao") // Fk para tabela Inscricao
    private Inscricao idInscricao;

    @OneToOne
    @JoinColumn(name = "matriculaAluno") // Fk para tabela Aluno
    private Aluno matriculaAluno;

}
