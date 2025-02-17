package com.uece.horas_complementares.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uece.horas_complementares.model.user.Aluno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Presenca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean presente = false;

    @ManyToOne
    @JoinColumn(name = "inscricao_id") // Define a coluna FK na tabela Presenca
    @JsonIgnore
    private Inscricao idInscricao;

    @ManyToOne
    @JoinColumn(name = "aluno_matricula") // Adicione esta anotação para definir a coluna FK
    @JsonIgnore
    private Aluno aluno;

    public Presenca() {
    }
    public boolean getPresente() {
        return presente;
    }

    public Presenca(boolean presente, Inscricao idInscricao, Aluno aluno) {
        this.presente = presente;
        this.idInscricao = idInscricao;
        this.aluno = aluno;
    }


}