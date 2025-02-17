package com.uece.horas_complementares.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uece.horas_complementares.model.user.Aluno;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class HoraComplementar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int horasComplementares;

    // Relacionamento 1:1 com Aluno (lado DONO)
    @OneToOne
    @JoinColumn(name = "aluno_matricula") // Coluna FK em HoraComplementar
    @JsonIgnore
    private Aluno aluno;

    @OneToMany(mappedBy = "horaComplementar")
    @JsonIgnore
    private List<SubCategoria> subCategorias;

}