package com.uece.horas_complementares.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class HoraComplementar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String status;
    private String comentario;

    // Relacionamento 1:1 com Aluno (lado DONO)
    @OneToOne
    @JoinColumn(name = "aluno_matricula") // Coluna FK em HoraComplementar
    private Aluno aluno; // Nome do campo deve bater com o mappedBy em Aluno

    @OneToMany(mappedBy = "horaComplementar")
    private List<SubCategoria> subCategorias;

    // Getters e Setters (ou Lombok @Data)
}