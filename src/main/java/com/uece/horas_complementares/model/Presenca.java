package com.uece.horas_complementares.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uece.horas_complementares.model.user.Aluno;
import jakarta.persistence.*;

@Entity
public class Presenca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inscricao_id") // Define a coluna FK na tabela Presenca
    @JsonIgnore
    private Inscricao idInscricao;

    @ManyToOne
    @JoinColumn(name = "aluno_matricula") // Adicione esta anotação para definir a coluna FK
    @JsonIgnore
    private Aluno aluno;
}