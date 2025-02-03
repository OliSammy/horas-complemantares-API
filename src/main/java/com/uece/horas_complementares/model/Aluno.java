package com.uece.horas_complementares.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@DiscriminatorValue("ALUNO")
public class Aluno extends User {

   // super();


    @ManyToOne
    @JoinColumn(name = "idCurso")
    private Curso curso;

    @OneToMany(mappedBy = "aluno") // Refere-se ao campo "aluno" em Inscricao
    private List<Inscricao> inscricoes;

    @OneToMany(mappedBy = "aluno") // Refere-se ao campo "aluno" em Presenca
    private List<Presenca> presencas;

    @OneToOne(mappedBy = "aluno") // Refere-se ao campo "aluno" em HoraComplementar
    private HoraComplementar horaComplementar;
}